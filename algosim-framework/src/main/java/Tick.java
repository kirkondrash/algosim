import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Tick {
    private long timestamp;
    private String currencyPair;
    private BigDecimal rate;
    private static DateTimeFormatter dtf  = DateTimeFormatter
            .ofPattern("yyyy.MM.dd HHmmss")
            .withZone(ZoneId.systemDefault());

    public Tick(String tickLine) {
        String[] lineValues = tickLine.split(",");
        Instant i = Instant.from(dtf.parse(lineValues[0]+" "+lineValues[1]));
        timestamp = i.getEpochSecond();
        currencyPair = lineValues[2];
        rate = new BigDecimal(lineValues[3]);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
