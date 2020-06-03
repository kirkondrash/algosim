import java.sql.SQLException;
import java.util.Iterator;

public class Simulation {
    public static void main(String[] args) throws TradingLogicException, SQLException {

        QuotesDAO quotes = new QuotesDAO();
        final TradingAlgorithm tradingAlgorithm = new TradingAlgorithmImpl();
        for (Iterator<Tick> tickIterator = quotes.getTicks("test_set_light").iterator(); tickIterator.hasNext(); ) {
            tradingAlgorithm.receiveTick(tickIterator.next());
        }

        tradingAlgorithm.evaluateResult();

        //quotes.getWindow("USD/RUB",0,0,5).forEach(System.out::println);

    }
}
