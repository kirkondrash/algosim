import java.util.ArrayList;
import java.util.List;

public class SimulationOrdersDAO {
    private List<SimulationOrder> orderList;

    public SimulationOrdersDAO() {
        orderList = new ArrayList<>();
    }

    public List<SimulationOrder> getOrders() {
        return orderList;
    }

    public void executeOrders(CurrentPrice currentPrice) {
        orderList.parallelStream()
                .filter(order -> order.getState().equals(SimulationOrder.State.OPENED))
                .filter(order ->
                    order.getAutoClosingPrices().stream().anyMatch( autoClosingPrice ->
                            ( currentPrice.get() >= autoClosingPrice && autoClosingPrice > currentPrice.getPrev()
                            || currentPrice.get() <= autoClosingPrice && autoClosingPrice < currentPrice.getPrev()))
                )
                .forEach(order -> {
                    order.close();
                    //System.out.println(order);
                });
        orderList.parallelStream()
                .filter(order -> order.getState().equals(SimulationOrder.State.WAIT))
                .filter(order -> (currentPrice.get() >= order.getOpeningPrice() && order.getOpeningPrice() > currentPrice.getPrev()
                        || currentPrice.get() <= order.getOpeningPrice() && order.getOpeningPrice() < currentPrice.getPrev()) )
                .forEach(order -> {
                    order.setState(SimulationOrder.State.OPENED);
                    //System.out.println(order);
                });
    }

    public void put(SimulationOrder order){
        orderList.add(order);
    }

    public double evaluateResult(){
        double res = orderList.parallelStream().filter(order -> order.getState().equals(SimulationOrder.State.CLOSED)).reduce(
                0.0,
                (acc,order) -> {
                    double delta = (order.getClosingPrice() - order.getOpeningPrice())*order.getLot();
                    if (order.getType()== SimulationOrder.Type.SELL)
                        delta*= -1;
                    return acc+delta;
                    },
                Double::sum);
        return res;
    }

}
