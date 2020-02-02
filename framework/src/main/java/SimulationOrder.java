import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class SimulationOrder extends Order implements OrderOperations<SimulationOrder>{

    enum State {WAIT, OPENED, CLOSED}
    enum Type {BUY, SELL}

    private State state;
    private Type type;
    @Inject
    private CurrentPrice currentPrice;
    List<Integer> autoClosingPrices = new ArrayList<>();

    public SimulationOrder(){
        state = State.WAIT;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Integer> getAutoClosingPrices() {
        return autoClosingPrices;
    }

    public void setAutoClosingPrices(List<Integer> autoClosingPrices) {
        this.autoClosingPrices = autoClosingPrices;
    }

    public SimulationOrder lot(int lot) {
        this.lot = lot;
        return this;
    }

    public SimulationOrder buyNow() {
        type =  Type.BUY;
        openingPrice = currentPrice.get();
        return this;
    }

    public SimulationOrder sellNow() {
        type = Type.SELL;
        openingPrice = currentPrice.get();
        return this;
    }

    public SimulationOrder buyStop(int pips) throws TradingLogicException {
        if (pips <= currentPrice.get())
            throw new TradingLogicException(String.format(
                    "Wrong move! BuyStop level [%d] has to be higher than current[%d]!",
                    pips,
                    currentPrice.get()));
        type = Type.BUY;
        openingPrice = pips;
        return this;
    }

    public SimulationOrder sellStop(int pips) throws TradingLogicException {
        if (pips >= currentPrice.get())
            throw new TradingLogicException(String.format(
                    "Wrong move! SellStop level [%d] has to be lower than current[%d]!",
                    pips,
                    currentPrice.get()));
        type = Type.SELL;
        openingPrice = pips;
        return this;
    }

    public SimulationOrder buyLimit(int pips) throws TradingLogicException {
        if (pips >= currentPrice.get())
            throw new TradingLogicException(String.format(
                    "Wrong move! BuyLimit level [%d] has to be lower than current[%d]!",
                    pips,
                    currentPrice.get()));
        type = Type.BUY;
        openingPrice = pips;
        return this;
    }

    public SimulationOrder sellLimit(int pips) throws TradingLogicException {
        if (pips <= currentPrice.get())
            throw new TradingLogicException(String.format(
                    "Wrong move! SellLimit level [%d] has to be higher than current[%d]!",
                    pips,
                    currentPrice.get()));
        type = SimulationOrder.Type.SELL;
        openingPrice = pips;
        return this;
    }

    public SimulationOrder stopLoss(int pips) throws TradingLogicException {
        switch (type){
            case BUY:
                if (pips >= openingPrice)
                    throw new TradingLogicException(String.format(
                            "Wrong move! Buying StopLoss level [%d] has to be lower than opening[%d]!",
                            pips,
                            openingPrice));
                break;
            case SELL:
                if (pips <= openingPrice)
                    throw new TradingLogicException(String.format(
                            "Wrong move! Selling StopLoss level [%d] has to be higher than opening[%d]!",
                            pips,
                            openingPrice));
                break;
            default:
                throw new TradingLogicException("Something went wrong! Not typed order!");

        }
        autoClosingPrices.add(pips);
        return this;
    }

    public SimulationOrder makeProfit(int pips) throws TradingLogicException {
        switch (type){
            case BUY:
                if (pips <= openingPrice)
                    throw new TradingLogicException(String.format(
                            "Wrong move! Buying MakeProfit level [%d] has to be higher than opening[%d]!",
                            pips,
                            openingPrice));
                break;
            case SELL:
                if (pips >= openingPrice)
                    throw new TradingLogicException(String.format(
                            "Wrong move! Selling MakeProfit level [%d] has to be lower than opening[%d]!",
                            pips,
                            openingPrice));
                break;
            default:
                throw new TradingLogicException("Something went wrong! Not typed order!");

        }
        autoClosingPrices.add(pips);
        return this;
    }

    public SimulationOrder close() {
        closingPrice = currentPrice.get();
        state = State.CLOSED;
        return this;
    }

    @Override
    public String toString() {
        return "SimulationOrder{" +
                "state=" + state +
                ", type=" + type +
                ", lot=" + lot +
                ", openingPrice=" + openingPrice +
                ", autoClosingPrices=" + autoClosingPrices +
                ", closingPrice=" + closingPrice +
                '}';
    }
}
