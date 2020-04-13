import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

public class TradingAlgorithmImpl implements TradingAlgorithm {
    private SimulationOrdersDAO ordersBase;
    private CurrencyRates currencyRates;
    private static Random r = new Random(System.currentTimeMillis());

    public TradingAlgorithmImpl() {
        ordersBase = new SimulationOrdersDAO();
        currencyRates = new CurrencyRates();
    }

    public void receiveTick(Tick tick) throws TradingLogicException {
        //System.out.println(Instant.ofEpochSecond(tick.getTimestamp()).toString());
        CurrencyRate currencyRate = currencyRates.updateAndReturnCurrentRate(tick.getCurrencyPair(),tick.getRate());

        /* put you trading logic here */

        if (r.nextBoolean()) { // imitation of favourable conditions
            double stopLoss = 0.001 * r.nextDouble(); // imitation of user choice of stop-loss level
            double makeProfit = 0.001 * r.nextDouble(); // imitation of user choice of make-profit level

            SimulationOrder o1 = new SimulationOrder(tick.getCurrencyPair(), currencyRate).buyNow();
            if (Arrays.asList(tick.getCurrencyPair().split("/")).contains("RUB")) {
                o1
                        .lot(50)
                        .stopLoss(tick.getRate().subtract(new BigDecimal(String.valueOf(stopLoss * 100))))
                        .makeProfit(tick.getRate().add(new BigDecimal(String.valueOf(makeProfit * 100))));
            } else {
                o1
                        .lot(10)
                        .stopLoss(tick.getRate().subtract(new BigDecimal(String.valueOf(stopLoss))))
                        .makeProfit(tick.getRate().add(new BigDecimal(String.valueOf(makeProfit))));
            }
            ordersBase.put(o1);
        }

        ordersBase.executeOrders();
    }

    @Override
    public void evaluateResult() {
        System.out.println(
                String.format("{ \"winloss\": %f, \"profitloss\": %f}",
                        ordersBase.evaluateWinLoss(),
                        ordersBase.evaluateProfitLoss()));
    }

}
