import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.sql.SQLException;
import java.time.Instant;

public abstract class TradingAlgorithm {
    private static Logger log = LogManager.getLogger(TradingAlgorithm.class);
    OrdersDAO ordersBase;
    CurrencyRates currencyRates;

    public TradingAlgorithm() throws SQLException {
        if (System.getProperty("framework.debug")!=null){
            Configurator.setLevel("TradingAlgorithm", Level.DEBUG);
        }
        currencyRates = new CurrencyRates();
        ordersBase = new OrdersDAO(currencyRates);
    }

    public void receiveTick(Tick tick) throws TradingLogicException, SQLException {
        CurrencyRate currencyRate = currencyRates.updateAndReturnCurrentRate(tick.getCurrencyPair(),tick.getRate());
        log.debug(String.format("%s - %s: %s",
                Instant.ofEpochSecond(tick.getTimestamp()).toString(),
                tick.getCurrencyPair(),
                currencyRate.toString()));

        handleTick(tick, currencyRate);

        ordersBase.executeOrders(tick.getCurrencyPair());
    }

    public abstract void handleTick(Tick tick, CurrencyRate currencyRate) throws SQLException;

    public void evaluateResult() throws SQLException {
        System.out.println(ordersBase.evaluateWinLoss());
        ordersBase.clearDB();
    }
}
