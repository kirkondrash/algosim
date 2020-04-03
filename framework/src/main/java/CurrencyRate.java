import java.math.BigDecimal;

public class CurrencyRate {
    private BigDecimal currentRate;
    private BigDecimal previousRate;

    public CurrencyRate(BigDecimal initialRate) {
        currentRate = initialRate;
        previousRate = initialRate;
    }

    public BigDecimal get() {
        return currentRate;
    }

    public void set(BigDecimal currentPrice) {
        previousRate = this.currentRate;
        this.currentRate = currentPrice;
    }

    public BigDecimal getPrev() {
        return previousRate;
    }

    @Override
    public String toString() {
        return "CurrencyRate{" +
                "currentRate=" + currentRate +
                ", previousRate=" + previousRate +
                '}';
    }
}
