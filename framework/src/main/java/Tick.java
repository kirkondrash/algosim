import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Tick {
    private long timestamp;
    private String currencyPair;
    private double rate;
    private static DateTimeFormatter dtf  = DateTimeFormatter
            .ofPattern("yyyy.MM.dd HHmmss")
            .withZone(ZoneId.systemDefault());

    public Tick(String tickLine) {
        String[] lineValues = tickLine.split(",");
        Instant i = Instant.from(dtf.parse(lineValues[0]+" "+lineValues[1]));
        timestamp = i.getEpochSecond();
        currencyPair = lineValues[2];
        rate = Double.parseDouble(lineValues[3]);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public double getRate() {
        return rate;
    }
}
