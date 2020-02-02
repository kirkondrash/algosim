import com.google.inject.Singleton;

@Singleton
public class CurrentPrice {
    private int currentPrice = 0;
    private int previousPrice = 0;

    public CurrentPrice() {}

    public int get() {
        return currentPrice;
    }

    public void set(int currentPrice) {
        previousPrice = this.currentPrice;
        this.currentPrice = currentPrice;
    }

    public int getPrev() {
        return previousPrice;
    }
}
