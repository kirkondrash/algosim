import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class OrdersDAO {
    private static Logger log = LogManager.getLogger(OrdersDAO.class);

    private List<Order> tickOrderList;
    private static CurrencyRates currencyRates;
    private Connection con;
    private PreparedStatement insertOrdersQuery, insertOrderTriggersQuery, openByTriggerQuery, closeByStopLossQuery, closeByTakeProfitQuery;
    private static Properties queries;
    private static final String algorithmId = System.getProperty("framework.algo_id");

    public OrdersDAO(CurrencyRates currencyRates) throws SQLException {
        if (System.getProperty("framework.debug")!=null){
            Configurator.setLevel("OrdersDAO",Level.DEBUG);
        }
        this.tickOrderList = new ArrayList<>();
        OrdersDAO.currencyRates = currencyRates;
        con = PostgreSQLManager.getConnection();
        queries = PostgreSQLManager.getQueries();
        insertOrdersQuery = con.prepareStatement(queries.getProperty("ORDER_INSERT_SQL"),new String[]{ "id" });
        insertOrderTriggersQuery = con.prepareStatement(queries.getProperty("ORDER_TRIGGER_INSERT_SQL"));
        openByTriggerQuery = con.prepareStatement(queries.getProperty("OPEN_BY_TRIGGER_SQL"));
        closeByStopLossQuery = con.prepareStatement(queries.getProperty("CLOSE_BY_STOP_LOSS_SQL"));
        closeByTakeProfitQuery = con.prepareStatement(queries.getProperty("CLOSE_BY_TAKE_PROFIT_SQL"));
    }

    public void executeOrders(String pair) throws SQLException {
        updateTriggeredOrders(pair);
        insertTickOrders();
        con.commit();
        tickOrderList.clear();
    }

    public void insertTickOrders() throws SQLException {
        for (Iterator<Order> orderIterator = tickOrderList.iterator(); orderIterator.hasNext(); ){
            Order o = orderIterator.next();
            insertOrdersQuery.setString(1,algorithmId);
            insertOrdersQuery.setString(2,o.getPair());
            insertOrdersQuery.setInt(3,o.getLot());
            insertOrdersQuery.setBigDecimal(4,o.getOpeningPrice());
            insertOrdersQuery.setString(5,o.getState().toString());
            insertOrdersQuery.setString(6,o.getType().toString());
            insertOrdersQuery.addBatch();
        }
        insertOrdersQuery.executeBatch();
        ResultSet keys = insertOrdersQuery.getGeneratedKeys();
        for (Iterator<Order> orderIterator = tickOrderList.iterator(); orderIterator.hasNext(); ){
            Order o = orderIterator.next();
            keys.next();
            Long orderKey = keys.getLong(1);
            for (Iterator<OrderTrigger> orderTriggerIterator = o.getTriggers().iterator(); orderTriggerIterator.hasNext(); ) {
                OrderTrigger ot = orderTriggerIterator.next();
                insertOrderTriggersQuery.setLong(1, orderKey);
                insertOrderTriggersQuery.setString(2, ot.getType().toString());
                insertOrderTriggersQuery.setBigDecimal(3,ot.getTrigger());
                insertOrderTriggersQuery.addBatch();
            }
        }
        insertOrderTriggersQuery.executeBatch();
    }

    public void updateTriggeredOrders(String pair) throws SQLException {
        CurrencyRate currencyRate = currencyRates.getCurrentRate(pair);
        closeByStopLossQuery.setBigDecimal(1,currencyRate.get());
        closeByStopLossQuery.setString(2,algorithmId);
        closeByStopLossQuery.setString(3,pair);
        closeByStopLossQuery.setBigDecimal(4,currencyRate.get());
        closeByStopLossQuery.setBigDecimal(5,currencyRate.getPrev());
        closeByStopLossQuery.execute();

        openByTriggerQuery.setBigDecimal(1,currencyRate.get());
        openByTriggerQuery.setString(2,algorithmId);
        openByTriggerQuery.setString(3,pair);
        openByTriggerQuery.setBigDecimal(4,currencyRate.get());
        openByTriggerQuery.setBigDecimal(5,currencyRate.getPrev());
        openByTriggerQuery.execute();

        closeByTakeProfitQuery.setBigDecimal(1,currencyRate.get());
        closeByTakeProfitQuery.setString(2,algorithmId);
        closeByTakeProfitQuery.setString(3,pair);
        closeByTakeProfitQuery.setBigDecimal(4,currencyRate.get());
        closeByTakeProfitQuery.setBigDecimal(5,currencyRate.getPrev());
        closeByTakeProfitQuery.execute();
    }

    public void put(Order order){
        tickOrderList.add(order);
    }

    public double evaluateWinLoss() throws SQLException {
        PreparedStatement winCountQuery = con.prepareStatement(queries.getProperty("GET_BUY_ORDER_SUCCESS_COUNT_SQL"));
        winCountQuery.setString(1,algorithmId);
        winCountQuery.execute();
        ResultSet rs = winCountQuery.getResultSet();
        rs.next();
        int winCount = rs.getInt(1);
        winCountQuery = con.prepareStatement(queries.getProperty("GET_SELL_ORDER_SUCCESS_COUNT_SQL"));
        winCountQuery.setString(1,algorithmId);
        winCountQuery.execute();
        rs = winCountQuery.getResultSet();
        rs.next();
        winCount += rs.getInt(1);
        PreparedStatement closedCountQuery = con.prepareStatement(queries.getProperty("GET_CLOSED_ORDER_COUNT_SQL"));
        closedCountQuery.setString(1,algorithmId);
        closedCountQuery.execute();
        rs = closedCountQuery.getResultSet();
        rs.next();
        int closedCount = rs.getInt(1);
        return (double) winCount / (closedCount - winCount);
    }

    public void clearDB() throws SQLException {
        PreparedStatement deleteOrders = con.prepareStatement(queries.getProperty("ORDER_DELETE_SQL"));
        deleteOrders.setString(1,algorithmId);
        deleteOrders.execute();
        con.commit();
    }

    private class ProfitLoss {
        BigDecimal profit;
        BigDecimal loss;

        public ProfitLoss() {
            profit = new BigDecimal("0");
            loss = new BigDecimal("0");
        }

        ProfitLoss add(ProfitLoss pl) {
            profit = profit.add(pl.profit);
            loss = loss.add(pl.loss);
            return this;
        }

        double ratio() {
            return profit.divide(loss,  RoundingMode.HALF_UP).doubleValue();
        }
    }

    public double evaluateProfitLoss(){ // TODO: multicurrency
//        ProfitLoss pl = closeOrderList
//                .parallelStream()
//                .reduce(new ProfitLoss(),
//                        (acc,order) -> {
//                    BigDecimal delta = order.getClosingPrice().subtract(order.getOpeningPrice());
//                    if (order.getType() == Order.Type.BUY && delta.signum() == 1 ||
//                            order.getType() == Order.Type.SELL && delta.signum() == -1)
//                        acc.profit = acc.profit.add(delta.abs());
//                    else
//                        acc.loss = acc.loss.add(delta.abs());
//                    return acc;
//                        },ProfitLoss::add);
//        return pl.ratio();
        return Double.valueOf("0.5");
    }
}
