import java.math.BigDecimal;
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

//    public double evaluateResult(CurrencyRates currencyRates){
//        double res = orderList.parallelStream().filter(order -> order.getState().equals(SimulationOrder.State.CLOSED)).reduce(
//                0.0,
//                (acc,order) -> {
//                    double delta = (order.getClosingPrice() - order.getOpeningPrice())*order.getLot();
//                    if (order.getType()== SimulationOrder.Type.SELL)
//                        delta*= -1;
//                    // TODO: how are multi-pair trading results calculated?
//                    return acc+delta;
//                },
//                Double::sum);
//        return res;
//    }

    private boolean isLevelCrossed(CurrencyRate currentPrice, BigDecimal level){
        int a1 = currentPrice.get().compareTo(level);
        int a2 = level.compareTo(currentPrice.getPrev());
        return a1 * a2 == 1 || a1 == 0;
    }
}
