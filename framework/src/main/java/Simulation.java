public class Simulation {
    public static void main(String[] args) {
        QuotesDAO quotes = new QuotesDAO("test_set_2");
        final TradingAlgorithm tradingAlgorithm = new TradingAlgorithmImpl();
        quotes.getTicks()
                .forEach( quote -> {
                    try {
                        tradingAlgorithm.receiveTick(quote);
                    } catch (TradingLogicException e) {
                        e.printStackTrace();
                    }
                });

        tradingAlgorithm.evaluateResult();

        //quotes.getWindow("USD/RUB",0,0,5).forEach(System.out::println);

    }
}
