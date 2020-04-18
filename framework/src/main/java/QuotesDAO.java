import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

public class QuotesDAO {
    private String fileName;

    public QuotesDAO(String fileName) {
        this.fileName = fileName;
    }

    public Stream<Tick> getTicks() {
        Stream<String> quoteStream = null;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(String.format("%s/%s.csv",System.getProperty("pathToQuotes","/quotes"), fileName)));
            quoteStream = br.lines().skip(1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return quoteStream.map(Tick::new);
    }

    //Sadly, only eager version by now..
    public Stream<Candlestick> getWindow(String pair, int hh, int mm, int ss){
        long nextClose = 0;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(String.format("%s/%s.csv", System.getProperty("pathToQuotes","/quotes"), fileName)));
            br.readLine();
            String[] lineValues = br.readLine().split(",");
            nextClose = Instant.from(DateTimeFormatter
                    .ofPattern("yyyy.MM.dd HHmmss")
                    .withZone(ZoneId.systemDefault()).parse(lineValues[0]+" "+lineValues[1])).getEpochSecond();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stream<Tick> tickStream =getTicks().filter(order -> order.getCurrencyPair().equals(pair));

        ArrayList<Candlestick> candleArray = new ArrayList<>();

        int dur = (hh*60+mm)*60+ss;
        Candlestick currentCandle = new Candlestick(nextClose,dur);
        BigDecimal prevQuote;
        BigDecimal curQuote = new BigDecimal(0);
        for (Iterator<Tick> i = tickStream.iterator(); i.hasNext(); ){
            Tick q = i.next();
            prevQuote = curQuote;
            curQuote = q.getRate();
            long curTime = q.getTimestamp();
            if (curTime >= nextClose) {
                candleArray.add(currentCandle.closingRate(prevQuote));
                nextClose += dur;
                while (curTime - nextClose >= 0) {
                    candleArray.add(new Candlestick(nextClose,dur));
                    nextClose += dur;
                }
                currentCandle = new Candlestick(nextClose,dur).openingRate(curQuote);
            }
            if(curQuote.compareTo(currentCandle.getHighRate())>0 || currentCandle.getHighRate().signum() == 0)
                currentCandle.setHighRate(curQuote);
            if (curQuote.compareTo(currentCandle.getLowRate())<0 || currentCandle.getLowRate().signum() == 0)
                currentCandle.setLowRate(curQuote);
        }
        return candleArray.stream();
    }
}
