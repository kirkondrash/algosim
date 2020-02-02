import com.google.inject.Guice;
import com.google.inject.Injector;

public class TradingAlgorithmImpl implements TradingAlgorithm {
    private SimulationOrdersDAO ordersBase;
    private Injector injector;
    private CurrentPrice currentPrice;

    public TradingAlgorithmImpl() {
        injector = Guice.createInjector(new GuiceModule());
        currentPrice = injector.getInstance(CurrentPrice.class);
        ordersBase = injector.getInstance(SimulationOrdersDAO.class);
    }

    public void receiveTick(int pips) throws TradingLogicException {
        currentPrice.set(pips);
        System.out.println(pips);
        SimulationOrder o1 = injector.getInstance(SimulationOrder.class)
                .lot(10)
                .buyNow()
                .stopLoss(pips-5)
                .makeProfit(pips+10);
        System.out.println(o1);
        ordersBase.put(o1);
        ordersBase.executeOrders();
    }

    @Override
    public void evaluateResult() {
        System.out.println(String.format("Total WL: %d", ordersBase.evaluateResult()));
    }

}
