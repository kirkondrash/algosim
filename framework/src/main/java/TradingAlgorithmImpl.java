public class TradingAlgorithmImpl implements TradingAlgorithm {
    private SimulationOrdersDAO ordersBase;
    private CurrentPrice currentPrice;

    public TradingAlgorithmImpl() {
        ordersBase = new SimulationOrdersDAO();
        currentPrice = new CurrentPrice();
    }

    public void receiveTick(Tick tick) throws TradingLogicException {
        currentPrice.set(tick.getRate());
        //System.out.println(tick.getRate());
        SimulationOrder o1 = new SimulationOrder(currentPrice)
                .lot(10)
                .buyNow()
                .stopLoss(tick.getRate()-0.1)
                .makeProfit(tick.getRate()+0.05);
        //System.out.println(o1);
        ordersBase.put(o1);
        ordersBase.executeOrders(currentPrice);
    }

    @Override
    public void evaluateResult() {
        System.out.println(String.format("Total WL: %f", ordersBase.evaluateResult()));
    }

}
