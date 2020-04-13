import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SimulationOrdersDAO {
    private Set<SimulationOrder> openOrderList;
    private Set<SimulationOrder> closeOrderList;
    private Set<SimulationOrder> waitOrderList;

    public SimulationOrdersDAO() {
        waitOrderList = ConcurrentHashMap.newKeySet();
        openOrderList = ConcurrentHashMap.newKeySet();
        closeOrderList = ConcurrentHashMap.newKeySet();
    }

    public void executeOrders() {

        openOrderList.parallelStream()
                .filter(order -> isLevelCrossed(order.getCurrencyRate(),order.getStopLossPrice()) ||
                        isLevelCrossed(order.getCurrencyRate(),order.getMakeProfitPrice()))
                .forEach(order -> {order.close();closeOrderList.add(order);openOrderList.remove(order);});

        waitOrderList.parallelStream()
                .filter(order -> isLevelCrossed(order.getCurrencyRate(), order.getOpeningPrice()))
                .map(order -> {openOrderList.add(order);waitOrderList.remove(order); return order.open();})
                // the case of scheduled order when rate went through opening and make-profit level at once
                .filter(order -> isLevelCrossed(order.getCurrencyRate(),order.getStopLossPrice()) &&
                        isLevelCrossed(order.getCurrencyRate(),order.getMakeProfitPrice()))
                .forEach(order -> {order.close();closeOrderList.add(order);openOrderList.remove(order);});
    }

    public void put(SimulationOrder order){
        waitOrderList.add(order);
    }

    public double evaluateWinLoss(){
        long winCount = closeOrderList
                .parallelStream()
                .filter(order -> {
                            double delta = order.getClosingPrice().subtract(order.getOpeningPrice()).doubleValue();
                            if (order.getType()== SimulationOrder.Type.SELL)
                                delta*= -1;
                            return delta > 0;
                        })
                .count();
        long closedCount = closeOrderList.size();
        return (double) winCount / (closedCount - winCount);
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
        ProfitLoss pl = closeOrderList
                .parallelStream()
                .reduce(new ProfitLoss(),
                        (acc,order) -> {
                    BigDecimal delta = order.getClosingPrice().subtract(order.getOpeningPrice());
                    if (order.getType() == SimulationOrder.Type.BUY && delta.signum() == 1 ||
                            order.getType() == SimulationOrder.Type.SELL && delta.signum() == -1)
                        acc.profit = acc.profit.add(delta.abs());
                    else
                        acc.loss = acc.loss.add(delta.abs());
                    return acc;
                        },ProfitLoss::add);
        return pl.ratio();
    }

    private boolean isLevelCrossed(CurrencyRate currentPrice, BigDecimal level){
        int a1 = currentPrice.get().compareTo(level);
        int a2 = level.compareTo(currentPrice.getPrev());
        return a1 * a2 == 1 || a1 == 0;
    }
}
