public class Candlestick {
    private double low = 0.0;
    private double high = 0.0;
    private double open = 0.0;
    private double close = 0.0;

    public Candlestick() {
    }

    public Candlestick(double low, double high, double open, double close) {
        this.low = low;
        this.high = high;
        this.open = open;
        this.close = close;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    @Override
    public String toString() {
        return "Candlestick{" +
                "low=" + low +
                ", high=" + high +
                ", open=" + open +
                ", close=" + close +
                '}';
    }
}
