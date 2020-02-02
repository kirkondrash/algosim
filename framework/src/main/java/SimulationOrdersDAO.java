import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class SimulationOrdersDAO {
    private List<SimulationOrder> orderList;
    @Inject
    private CurrentPrice currentPrice;

    public SimulationOrdersDAO() {
        orderList = new ArrayList<>();
    }

    public List<SimulationOrder> getOrders() {
        return orderList;
    }

    public void executeOrders() {
        orderList.parallelStream()
                .filter(order -> order.getState().equals(SimulationOrder.State.OPENED))
                .filter(order ->
                    order.getAutoClosingPrices().stream().anyMatch( autoClosingPrice ->
                            ( currentPrice.get() >= autoClosingPrice && autoClosingPrice > currentPrice.getPrev()
                            || currentPrice.get() <= autoClosingPrice && autoClosingPrice < currentPrice.getPrev()))
                )
                .forEach(order -> {
                    order.close();
                    System.out.println(order);
                });
        orderList.parallelStream()
                .filter(order -> order.getState().equals(SimulationOrder.State.WAIT))
                .filter(order -> (currentPrice.get() >= order.getOpeningPrice() && order.getOpeningPrice() > currentPrice.getPrev()
                        || currentPrice.get() <= order.getOpeningPrice() && order.getOpeningPrice() < currentPrice.getPrev()) )
                .forEach(order -> {
                    order.setState(SimulationOrder.State.OPENED);
                    System.out.println(order);
                });
    }

    public void put(SimulationOrder order){
        orderList.add(order);
    }

    public int evaluateResult(){
        int res = orderList.parallelStream().filter(order -> order.getState().equals(SimulationOrder.State.CLOSED)).reduce(
                0,
                (acc,order) -> {
                    int delta = (order.getClosingPrice() - order.getOpeningPrice())*order.getLot();
                    if (order.getType()== SimulationOrder.Type.SELL)
                        delta*= -1;
                    return acc+delta;
                    },
                Integer::sum);
        return res;
    }

}
