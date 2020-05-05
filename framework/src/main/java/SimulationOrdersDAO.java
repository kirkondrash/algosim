import org.apache.logging.log4j.*;
import org.apache.logging.log4j.core.config.Configurator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SimulationOrdersDAO {
    private static Logger log = LogManager.getLogger(SimulationOrdersDAO.class);

    private List<Order> tickOrderList;
    private static CurrencyRates currencyRates;
    private Session session;
    private Query triggeredOrdersQuery;
    private static Predicate<Order> isClosing = order -> order.getState().equals(Order.State.OPENED) &&
            (order.isTriggered(OrderTrigger.Type.MAKEPROFIT,currencyRates.getCurrentRate(order.getPair())) ||
            order.isTriggered(OrderTrigger.Type.STOPLOSS,currencyRates.getCurrentRate(order.getPair())));
    private static Predicate<Order> isOpening = order -> order.getState().equals(Order.State.WAIT) &&
            order.isTriggered(OrderTrigger.Type.OPEN,currencyRates.getCurrentRate(order.getPair()));
    private static Predicate<Order> isSkippping = order -> order.getState().equals(Order.State.OPENED) &&
            order.isTriggered(OrderTrigger.Type.MAKEPROFIT,currencyRates.getCurrentRate(order.getPair())) &&
            order.isTriggered(OrderTrigger.Type.STOPLOSS,currencyRates.getCurrentRate(order.getPair()));

    public SimulationOrdersDAO(CurrencyRates currencyRates) {
        if (System.getProperty("framework.debug")!=null){
            Configurator.setLevel("SimulationOrdersDAO",Level.DEBUG);
        }

        this.session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        this.triggeredOrdersQuery = session.createQuery("from Order as orders join fetch orders.triggers as order_triggers where " +
                "((order_triggers.trigger <= :curprice and :prevprice < order_triggers.trigger) or " +
                "(order_triggers.trigger < :prevprice and :curprice <= order_triggers.trigger)) and " +
                "orders.pair = :pair and " +
                "orders.state != 'CLOSED'");
        this.tickOrderList = new ArrayList<>();
        SimulationOrdersDAO.currencyRates = currencyRates;
    }

    public void executeOrders(String pair) {
        Transaction tx = session.beginTransaction();
        //change statuses of all orders after new currency rate
        List<Order> triggeredOrderList = getTriggeredOrders(pair);
        Set<Order> updatedOrderList = processTriggeredOrders(triggeredOrderList);
        updateTriggeredOrders(updatedOrderList);
        insertTickOrders();
        tickOrderList.clear();
        tx.commit();
    }

    public List<Order> getTriggeredOrders(String pair){
        CurrencyRate rate = currencyRates.getCurrentRate(pair);
        triggeredOrdersQuery.setParameter("curprice", rate.get());
        triggeredOrdersQuery.setParameter("prevprice",rate.getPrev());
        triggeredOrdersQuery.setParameter("pair", pair);
        return triggeredOrdersQuery.list();
    }

    public Set<Order> processTriggeredOrders(List<Order> triggeredOrderList){
        Set<Order> updatedOrderList = new HashSet<>();
        updatedOrderList.addAll(
                triggeredOrderList.parallelStream()
                        .filter(isClosing)
                        .map(order -> order.close(currencyRates.getCurrentRate(order.getPair())))
                        .collect(Collectors.toSet()));
        updatedOrderList.addAll(
                triggeredOrderList.parallelStream()
                        .filter(isOpening)
                        .map(order -> order.open(currencyRates.getCurrentRate(order.getPair())))
                        .collect(Collectors.toSet()));
        updatedOrderList.parallelStream()
                .filter(isSkippping)
                .forEach(order -> order.close(currencyRates.getCurrentRate(order.getPair())));

        return updatedOrderList;
    }

    public void insertTickOrders(){
        int count = 0;
        //persist of all new orders for this tick
        for (Iterator<Order> iter = tickOrderList.iterator(); iter.hasNext(); ){
            Order o = iter.next();
            session.persist("orders",o);
            log.debug(String.format("   inserting %s", o.toString()));
            if ( ++count % 40 == 0 ) { //40, same as the JDBC batch size
                //flush a batch of inserts and release memory:
                session.flush();
                session.clear();
            }
        }
        count = 0;
        session.flush();
        session.clear();
        List<OrderTrigger> orderTriggerList = tickOrderList.stream().flatMap(order -> order.getTriggers().stream()).collect(Collectors.toList());
        //persist of all new orders triggers for this tick
        for (Iterator<OrderTrigger> iter = orderTriggerList.iterator(); iter.hasNext(); ){
            OrderTrigger orderTrigger = iter.next();
            session.persist(orderTrigger);
            if ( ++count % 40 == 0 ) { //40, same as the JDBC batch size
                //flush a batch of inserts and release memory:
                session.flush();
                session.clear();
            }
        }
        count = 0;
        session.flush();
        session.clear();
    }

    public void updateTriggeredOrders(Set<Order> triggeredOrderList){
        int count =0;
        //update of all orders after new currency rate
        for (Iterator<Order> iter = triggeredOrderList.iterator(); iter.hasNext(); ){
            Order o = iter.next();
            log.debug(String.format("   updating %d: %s", o.getId(),o.toString()));
            session.update(o);
            if ( ++count % 40 == 0 ) { //40, same as the JDBC batch size
                //flush a batch of inserts and release memory:
                session.flush();
                session.clear();
            }
        }
        session.flush();
        session.clear();
    }

    public void put(Order order){
        tickOrderList.add(order);
    }

    public double evaluateWinLoss(){
        Query q = session.createQuery("from Order as orders where orders.state='CLOSED'");
        List<Order> orderList = q.list();
        long winCount = orderList
                .parallelStream()
                .filter(order -> {
                            double delta = order.getClosingPrice().subtract(order.getOpeningPrice()).doubleValue();
                            if (order.getType()== Order.Type.SELL)
                                delta*= -1;
                            return delta > 0;
                        })
                .count();
        long closedCount = orderList.size();
        return (double) winCount / (closedCount - winCount);
    }

    public void clearDB(){
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("DELETE OrderTrigger");
        query.executeUpdate();
        query = session.createQuery("DELETE Order");
        query.executeUpdate();
        tx.commit();
        session.close();
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
