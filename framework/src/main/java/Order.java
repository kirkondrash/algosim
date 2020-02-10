import java.util.ArrayList;
import java.util.List;

public abstract class Order {
    int lot;
    double openingPrice = 0;
    double closingPrice = 0;


    public int getLot() {
        return lot;
    }

    public void setLot(int lot) {
        this.lot = lot;
    }

    public double getOpeningPrice() {
        return openingPrice;
    }

    public void setOpeningPrice(double openingPrice) {
        this.openingPrice = openingPrice;
    }

    public double getClosingPrice() { return closingPrice; }

    public void setClosingPrice(double closingPrice) {
        this.closingPrice = closingPrice;
    }

}
