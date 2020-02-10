import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class QuotesDAO {
    private String fileName;

    public QuotesDAO(String base, String quote, String yyyy, String mm, String dd) {
        fileName = String.join(
                "_",
                Arrays.asList(
                        base.toUpperCase(),
                        quote.toUpperCase(),
                        yyyy,
                        mm,
                        dd));
    }

    public DoubleStream getTicks(){
        Stream<String> quoteStream = null;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(String.format("/Users/kirkondrash/quotes/%s.csv", fileName)));
            quoteStream = br.lines().skip(1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return quoteStream.mapToDouble(q -> Double.parseDouble(q.split(",")[1]));
    }

    //Sadly, only eager version by now..
    public Stream<Candlestick> getWindow(int hh, int mm, int ss){
        Stream<String> quoteStream = null;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(String.format("/Users/kirkondrash/quotes/%s.csv", fileName)));
            quoteStream = br.lines().skip(1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Candlestick> candleArray = new ArrayList<>();
        int dur = 60*hh+60*mm+ss;
        int nextOpen = 0;
        int nextClose = 0;
        double prevQuote;
        double curQuote = 0.0;
        double lowQuote = 0.0;
        double highQuote = 0.0;
        double openQuote = 0.0;
        for (Iterator<String> i = quoteStream.iterator(); i.hasNext(); ){
            String q = i.next();
            String[] line = q.split(",");
            prevQuote = curQuote;
            curQuote = Double.parseDouble(line[1]);
            int curTime = LocalTime.parse(line[0], DateTimeFormatter.ofPattern("HHmmss")).toSecondOfDay();
            if (curTime >= nextClose) {
//                System.out.println(String.format("Close window %s", LocalTime.ofSecondOfDay(nextClose)));
                candleArray.add(new Candlestick(lowQuote, highQuote, openQuote, prevQuote));
                lowQuote = 0.0;
                highQuote = 0.0;
//                System.out.println(String.format("Open window %s", LocalTime.ofSecondOfDay(nextClose)));
                nextClose +=dur;
                openQuote = curQuote;
                while (curTime - nextClose >= 0) {
//                    System.out.println(String.format("Close window %s", LocalTime.ofSecondOfDay(nextClose)));
                    candleArray.add(new Candlestick());
//                    System.out.println(String.format("Open window %s", LocalTime.ofSecondOfDay(nextClose)));
                    nextClose += dur;
                }
            }
            if(curQuote>highQuote || highQuote == 0)
                highQuote = curQuote;
            if (curQuote<lowQuote || lowQuote == 0)
                lowQuote = curQuote;
        }
        return candleArray.stream();
    }
}
