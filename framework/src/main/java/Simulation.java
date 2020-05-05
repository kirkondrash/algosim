import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

public class Simulation {
    public static void main(String[] args) throws TradingLogicException {

        QuotesDAO quotes = new QuotesDAO();
        final TradingAlgorithm tradingAlgorithm = new TradingAlgorithmImpl();
        quotes.getTicks("test_set_light")
                .forEach( quote -> {
                        tradingAlgorithm.receiveTick(quote);
                });

        tradingAlgorithm.evaluateResult();

        //quotes.getWindow("USD/RUB",0,0,5).forEach(System.out::println);

    }
}
