import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Order implements OrderOperations<Order>{

    enum State {WAIT, OPENED, CLOSED}
    enum Type {BUY, SELL}

    private int id;
    private String pair;
    private int lot;
    private BigDecimal openingPrice;
    private BigDecimal closingPrice;
    private State state;
    private Type type;
    private List<OrderTrigger> triggers;

    private CurrencyRate currencyRate;

    public Order(CurrencyRate currencyRate, String pair, int lot) {
        this.state = State.WAIT;
        this.pair = pair;
        this.currencyRate = currencyRate;
        this.lot = lot;
        this.triggers = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getPair() {
        return pair;
    }

    public CurrencyRate getCurrencyRate() {
        return currencyRate;
    }

    public int getLot() {
        return lot;
    }

    public BigDecimal getOpeningPrice() {
        return openingPrice;
    }

    public BigDecimal getClosingPrice() {
        return closingPrice;
    }

    public State getState() {
        return state;
    }

    public Type getType() {
        return type;
    }

    public List<OrderTrigger> getTriggers() {
        return triggers;
    }

    public Order buyNow() throws TradingLogicException {
        if (state != State.WAIT)
            throw new TradingLogicException("Wrong move! Buy can be applied only to not opened orders!");
        type =  Type.BUY;
        open(currencyRate);
        return this;
    }

    public Order sellNow() throws TradingLogicException {
        if (state != State.WAIT)
            throw new TradingLogicException("Wrong move! Sell can be applied only to not opened orders!");
        type = Type.SELL;
        open(currencyRate);
        return this;
    }

    public Order buyStop(BigDecimal pips) throws TradingLogicException {
        if (state != State.WAIT)
            throw new TradingLogicException("Wrong move! BuyStop can be applied only to not opened orders!");
        if (pips.compareTo(currencyRate.get()) != 1)
            throw new TradingLogicException(String.format(
                    "Wrong move! BuyStop level [%.5f] has to be higher than current[%.5f]!",
                    pips,
                    currencyRate.get()));
        type = Type.BUY;
        openingPrice = pips;
        addTrigger(OrderTrigger.Type.OPEN,pips);
        return this;
    }

    public Order sellStop(BigDecimal pips) throws TradingLogicException {
        if (state != State.WAIT)
            throw new TradingLogicException("Wrong move! SellStop can be applied only to not opened orders!");
        if (pips.compareTo(currencyRate.get()) != -1)
            throw new TradingLogicException(String.format(
                    "Wrong move! SellStop level [%.5f] has to be lower than current[%.5f]!",
                    pips,
                    currencyRate.get()));
        type = Type.SELL;
        openingPrice = pips;
        addTrigger(OrderTrigger.Type.OPEN,pips);
        return this;
    }

    public Order buyLimit(BigDecimal pips) throws TradingLogicException {
        if (state != State.WAIT)
            throw new TradingLogicException("Wrong move! BuyLimit can be applied only to not opened orders!");
        if (pips.compareTo(currencyRate.get()) != -1)
            throw new TradingLogicException(String.format(
                    "Wrong move! BuyLimit level [%.5f] has to be lower than current[%.5f]!",
                    pips,
                    currencyRate.get()));
        type = Type.BUY;
        openingPrice = pips;
        addTrigger(OrderTrigger.Type.OPEN,pips);
        return this;
    }

    public Order sellLimit(BigDecimal pips) throws TradingLogicException {
        if (state != State.WAIT)
            throw new TradingLogicException("Wrong move! SellLimit can be applied only to not opened orders!");
        if (pips.compareTo(currencyRate.get()) != 1)
            throw new TradingLogicException(String.format(
                    "Wrong move! SellLimit level [%.5f] has to be higher than current[%.5f]!",
                    pips,
                    currencyRate.get()));
        type = Order.Type.SELL;
        openingPrice = pips;
        addTrigger(OrderTrigger.Type.OPEN,pips);
        return this;
    }

    public Order stopLoss(BigDecimal pips) throws TradingLogicException {
        switch (type) {
            case BUY:
                if (pips.compareTo(openingPrice) != -1)
                    throw new TradingLogicException(String.format(
                            "Wrong move! Buying StopLoss level [%.5f] has to be lower than opening[%.5f]!",
                            pips,
                            openingPrice));
                break;
            case SELL:
                if (pips.compareTo(openingPrice) != 1)
                    throw new TradingLogicException(String.format(
                            "Wrong move! Selling StopLoss level [%.5f] has to be higher than opening[%.5f]!",
                            pips,
                            openingPrice));
                break;
            default:
                throw new TradingLogicException("Something went wrong! Not typed order!");

        }
        addTrigger(OrderTrigger.Type.STOPLOSS, pips);
        return this;
    }

    public Order takeProfit(BigDecimal pips) throws TradingLogicException {
        switch (type) {
            case BUY:
                if (pips.compareTo(openingPrice) != 1)
                    throw new TradingLogicException(String.format(
                            "Wrong move! Buying TakeProfit level [%.5f] has to be higher than opening[%.5f]!",
                            pips,
                            openingPrice));
                break;
            case SELL:
                if (pips.compareTo(openingPrice) != -1)
                    throw new TradingLogicException(String.format(
                            "Wrong move! Selling TakeProfit level [%.5f] has to be lower than opening[%.5f]!",
                            pips,
                            openingPrice));
                break;
            default:
                throw new TradingLogicException("Something went wrong! Not typed order!");

        }
        addTrigger(OrderTrigger.Type.TAKEPROFIT, pips);
        return this;
    }

    public Order close(CurrencyRate currencyRate) {
        closingPrice = currencyRate.get();
        state = State.CLOSED;
        return this;
    }

    private Order open(CurrencyRate currencyRate) {
        openingPrice = currencyRate.get();
        state = State.OPENED;
        return this;
    }

    @Override
    public String toString() {
        return String.format(
                "SimulationOrder{ " +
                        "state=%s, " +
                        "type=%s, " +
                        "pair=%s, " +
                        "lot=%s, " +
                        "openingPrice=%.5f, " +
                        "closingPrice=%s, " +
                        "triggers=%s}",
                state,
                type,
                pair,
                lot,
                openingPrice,
                closingPrice,
                triggers.toString());
    }

    private void addTrigger(OrderTrigger.Type type, BigDecimal trigger){
        OrderTrigger ot = new OrderTrigger(type,trigger);
        triggers.removeIf(t -> t.getType().equals(type));
        triggers.add(ot);
    }

    private boolean isLevelCrossed(CurrencyRate currentRate, BigDecimal level){
        int a1 = currentRate.get().compareTo(level);
        int a2 = level.compareTo(currentRate.getPrev());
        return a1 * a2 == 1 || a1 == 0;
    }
}
