public class Simulation {
    public static void main(String[] args) {
        QuotesDAO quotes = new QuotesDAO("test_set_2");
        final TradingAlgorithm tradingAlgorithm = new TradingAlgorithmImpl();
        quotes.getTicks()
                .filter(tick -> tick.getCurrencyPair().equals("EUR/RUB"))
                .forEach( quote -> {
                    try {
                        tradingAlgorithm.receiveTick(quote);
                    } catch (TradingLogicException e) {
                        e.printStackTrace();
                    }
                });

        tradingAlgorithm.evaluateResult();

        //quotes.getWindow(0,0,10).forEach(System.out::println);

    }
}
