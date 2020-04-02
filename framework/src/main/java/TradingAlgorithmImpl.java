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

    public void receiveTick(Tick tick) throws TradingLogicException {
        currentPrice.set(tick.getRate());
        //System.out.println(tick.getRate());
        SimulationOrder o1 = injector.getInstance(SimulationOrder.class)
                .lot(10)
                .buyNow()
                .stopLoss(tick.getRate()-0.1)
                .makeProfit(tick.getRate()+0.05);
        //System.out.println(o1);
        ordersBase.put(o1);
        ordersBase.executeOrders();
    }

    @Override
    public void evaluateResult() {
        System.out.println(String.format("Total WL: %f", ordersBase.evaluateResult()));
    }

}
