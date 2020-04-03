import java.math.BigDecimal;
import java.time.Instant;

public class Candlestick {
    private Instant openingTime;
    private Instant closingTime;
    private BigDecimal lowRate;
    private BigDecimal highRate;
    private BigDecimal openingRate;
    private BigDecimal closingRate;

    public Candlestick(long close, long dur) {
        lowRate = new BigDecimal(0);
        highRate = new BigDecimal(0);
        openingRate = new BigDecimal(0);
        closingRate = new BigDecimal(0);
        openingTime = Instant.ofEpochSecond(close-dur);
        closingTime = Instant.ofEpochSecond(close);
    }

    public Candlestick openingRate(BigDecimal openingRate) {
        this.openingRate = openingRate;
        return this;
    }

    public Candlestick closingRate(BigDecimal closingRate) {
        this.closingRate = closingRate;
        return this;
    }

    public BigDecimal getLowRate() {
        return lowRate;
    }

    public void setLowRate(BigDecimal lowRate) {
        this.lowRate = lowRate;
    }

    public BigDecimal getHighRate() {
        return highRate;
    }

    public void setHighRate(BigDecimal highRate) {
        this.highRate = highRate;
    }

    @Override
    public String toString() {
        return "Candlestick{" +
                "openingTime=" + openingTime +
                ", closingTime=" + closingTime +
                ", lowRate=" + lowRate +
                ", highRate=" + highRate +
                ", openingRate=" + openingRate +
                ", closingRate=" + closingRate +
                '}';
    }
}
