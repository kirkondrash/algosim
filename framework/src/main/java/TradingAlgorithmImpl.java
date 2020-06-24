import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;

public class TradingAlgorithmImpl extends TradingAlgorithm {
    private static Random r = new Random(System.currentTimeMillis());
    /* the user may have some data structure to store calculation intermediate results between the ticks */

    public TradingAlgorithmImpl() throws SQLException {
        super();
        /* the user may initialize his data structures here */
    }

    /* The user has to implement handleTick method - fill it with decision making logic.
       The user is supplied with an Tick object and a CurrencyRate object.
       CurrencyRate object is mostly needed for correct Order initialisation.
       Tick class has methods:
        - getTimestamp() - returns time of the tick, a long,
        - getCurrencyPair() - returns name of the currency pair in a "USD/RUB" fashion,
        - getRate() - returns the BigDecimal type currency rate.
     */
    public void handleTick(Tick tick, CurrencyRate currencyRate) throws TradingLogicException {

        /* put you trading logic here */

        /* random boolean here is an imitation of favourable conditions decided by the user */
        if (r.nextBoolean()) {
            /* imitation of user choice of stop loss and take profit level */
            double stopLoss = 0.001 * r.nextDouble();
            double takeProfit = 0.001 * r.nextDouble();

            /* The very core of trading operations is an Order object.
            *  It constructor takes three parameters - CurrencyRate object, currency pair name (string) and lot value (int).
            *  The Order class methods to be used here (in a builder fashion) are:
            *  - buyNow() - open order to buy currency pair right now,
            *  - sellNow() - open order to sell currency pair right now,
            *  - buyStop(BigDecimal pips) - open order to buy currency pair when the rate goes up the 'pips' value,
            *  - sellStop(BigDecimal pips) - open order to sell currency pair when the rate goes down the 'pips' value,
            *  - buyLimit(BigDecimal pips) - open order to buy currency pair when the rate goes down the 'pips' value,
            *  - sellLimit(BigDecimal pips) - open order to buy currency pair when the rate goes up the 'pips' value,
            *  - stopLoss(BigDecimal pips) - close order to reduce the losses when the rate goes through the 'pips' value,
            *  - takeProfit(BigDecimal pips) - open order to preserve the profits when the rate goes up the 'pips' value.
             */

            /* For example, different trade tactics for currency pairs including russian rouble */
            if (tick.getCurrencyPair().contains("RUB")) {
                Order o1 = new Order(currencyRate, tick.getCurrencyPair(), 50)
                        .buyNow()
                        .stopLoss(tick.getRate().subtract(new BigDecimal(String.valueOf(stopLoss * 100))));
                /* all orders MUST be put into the order base to be taken into account */
                ordersBase.put(o1);
            } else {
                Order o2 = new Order(currencyRate, tick.getCurrencyPair(), 10)
                        .sellStop(tick.getRate().subtract(new BigDecimal("0.05")))
                        .stopLoss(tick.getRate().add(new BigDecimal(String.valueOf(stopLoss))))
                        .takeProfit(tick.getRate().subtract(new BigDecimal(String.valueOf(takeProfit))));
                /* all orders MUST be put into the order base to be taken into account */
                ordersBase.put(o2);
            }
        }
    }

}
