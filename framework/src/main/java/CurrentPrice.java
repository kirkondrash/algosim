public class CurrentPrice {
    private double currentPrice = 0;
    private double previousPrice = 0;

    public CurrentPrice() {}

    public double get() {
        return currentPrice;
    }

    public void set(double currentPrice) {
        previousPrice = this.currentPrice;
        this.currentPrice = currentPrice;
    }

    public double getPrev() {
        return previousPrice;
    }
}
