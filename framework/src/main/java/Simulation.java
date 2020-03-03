public class Simulation {
    public static void main(String[] args) {
        QuotesDAO eurusd = new QuotesDAO(
                "EUR",
                "RUB",
                "2020",
                "01",
                "20");
        final TradingAlgorithm tradingAlgorithm = new TradingAlgorithmImpl();
        eurusd.getTicks().forEach( quote -> {
            try {
                tradingAlgorithm.receiveTick(quote);
            } catch (TradingLogicException e) {
                e.printStackTrace();
            }
        });
        tradingAlgorithm.evaluateResult();

        eurusd.getWindow(0,0,10).forEach(System.out::println);

    }
}
