import java.math.BigDecimal;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SimulationOrdersDAO {
    private Set<SimulationOrder> orderSet;

    public SimulationOrdersDAO() {
        orderSet = ConcurrentHashMap.newKeySet();
    }

    public void executeOrders() {

        orderSet.parallelStream()
                .filter(order -> order.getState().equals(SimulationOrder.State.OPENED))
                .filter(order -> order.getAutoClosingPrices().stream().anyMatch(
                                autoClosingPrice -> isLevelCrossed(order.getCurrencyRate(),autoClosingPrice)))
                .forEach(order -> order.close());

        orderSet.parallelStream()
                .filter(order -> order.getState().equals(SimulationOrder.State.WAIT))
                .filter(order -> isLevelCrossed(order.getCurrencyRate(), order.getOpeningPrice()))
                .map(order -> order.open())
                // the case of scheduled order when rate went through opening and make-profit level at once
                .filter(order -> order.getAutoClosingPrices().stream().allMatch(
                        autoClosingPrice -> isLevelCrossed(order.getCurrencyRate(),autoClosingPrice)))
                .forEach(order -> order.close());
    }

    public void put(SimulationOrder order){
        orderSet.add(order);
    }

    public double evaluateWinLoss(){
        long winCount = orderSet
                .parallelStream()
                .filter(order -> order.getState().equals(SimulationOrder.State.CLOSED))
                .filter(order -> {
                            double delta = order.getClosingPrice().subtract(order.getOpeningPrice()).doubleValue();
                            if (order.getType()== SimulationOrder.Type.SELL)
                                delta*= -1;
                            return delta > 0;
                        })
                .count();
        long closedCount = orderSet
                .parallelStream()
                .filter(order -> order.getState().equals(SimulationOrder.State.CLOSED))
                .count();
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
