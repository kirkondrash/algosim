import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {
    public static void main(String[] args) {
        List<Integer> quotes = new ArrayList();
        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            quotes.add(r.nextInt(50));
        }
        final TradingAlgorithm tradingAlgorithm = new TradingAlgorithmImpl();
        for (Integer quote : quotes) {
            try {
                tradingAlgorithm.receiveTick(quote);
            } catch (TradingLogicException e) {
                e.printStackTrace();
            }
        }
        tradingAlgorithm.evaluateResult();

    }
}
