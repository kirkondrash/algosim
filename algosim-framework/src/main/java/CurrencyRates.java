import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CurrencyRates {
    private Map<String, CurrencyRate> currencyRateMap;

    public CurrencyRates() {
        currencyRateMap = new HashMap<>();
    }

    public CurrencyRate getCurrentRate(String pair){// throws TradingLogicException {
        CurrencyRate currentPrice =  currencyRateMap.get(pair);
        //if (currentPrice == null)
        //    throw  new TradingLogicException("No current price for this pair!");
        return currentPrice;
    }

    public CurrencyRate updateAndReturnCurrentRate(String pair, BigDecimal rate) {
        CurrencyRate currencyRate = currencyRateMap.getOrDefault(pair,new CurrencyRate(rate));
        currencyRate.set(rate);
        currencyRateMap.put(pair, currencyRate);
        return currencyRate;
    }
}
