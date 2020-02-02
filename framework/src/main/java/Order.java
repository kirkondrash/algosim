import java.util.ArrayList;
import java.util.List;

public abstract class Order {
    int lot;
    int openingPrice = 0;
    int closingPrice = 0;


    public int getLot() {
        return lot;
    }

    public void setLot(int lot) {
        this.lot = lot;
    }

    public int getOpeningPrice() {
        return openingPrice;
    }

    public void setOpeningPrice(int openingPrice) {
        this.openingPrice = openingPrice;
    }

    public int getClosingPrice() { return closingPrice; }

    public void setClosingPrice(int closingPrice) {
        this.closingPrice = closingPrice;
    }

}
