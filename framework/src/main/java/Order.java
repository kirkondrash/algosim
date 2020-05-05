import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name="orders")
public class Order implements OrderOperations<Order>{

    enum State {WAIT, OPENED, CLOSED}
    enum Type {BUY, SELL}

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orders_sequence"
    )
    @SequenceGenerator(
            name = "orders_sequence",
            sequenceName = "orders_id_seq",
            allocationSize = 40
    )
    private int id;
    @Column(name = "pair")
    private String pair;
    @Column(name = "lot")
    private int lot;
    @Column(name = "opening_price")
    private BigDecimal openingPrice;
    @Column(name = "closing_price")
    private BigDecimal closingPrice;
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;
    @OneToMany(mappedBy = "order")
    private List<OrderTrigger> triggers;

    @Transient
    private CurrencyRate currencyRate;

    public Order() {
    }

    public Order(CurrencyRate currencyRate, String pair) {
        this.state = State.WAIT;
        this.pair = pair;
        this.currencyRate = currencyRate;
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

    public void setLot(int lot) {
        this.lot = lot;
    }

    public BigDecimal getOpeningPrice() {
        return openingPrice;
    }

    public void setOpeningPrice(BigDecimal openingPrice) {
        this.openingPrice = openingPrice;
    }

    public BigDecimal getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(BigDecimal closingPrice) {
        this.closingPrice = closingPrice;
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

    public List<OrderTrigger> getTriggers() {
        return triggers;
    }

    public void setTriggers(List<OrderTrigger> triggers) {
        this.triggers = triggers;
    }

    public Order lot(int lot) {
        this.lot = lot;
        return this;
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

    public Order makeProfit(BigDecimal pips) throws TradingLogicException {
        switch (type) {
            case BUY:
                if (pips.compareTo(openingPrice) != 1)
                    throw new TradingLogicException(String.format(
                            "Wrong move! Buying MakeProfit level [%.5f] has to be higher than opening[%.5f]!",
                            pips,
                            openingPrice));
                break;
            case SELL:
                if (pips.compareTo(openingPrice) != -1)
                    throw new TradingLogicException(String.format(
                            "Wrong move! Selling MakeProfit level [%.5f] has to be lower than opening[%.5f]!",
                            pips,
                            openingPrice));
                break;
            default:
                throw new TradingLogicException("Something went wrong! Not typed order!");

        }
        addTrigger(OrderTrigger.Type.MAKEPROFIT, pips);
        return this;
    }

    public Order close(CurrencyRate currencyRate) {
        closingPrice = currencyRate.get();
        state = State.CLOSED;
        return this;
    }

    public Order open(CurrencyRate currencyRate) {
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

    public void addTrigger(OrderTrigger.Type type, BigDecimal trigger){
        OrderTrigger ot = new OrderTrigger(type,trigger);
        ot.setOrder(this);
        triggers.removeIf(t -> t.getType().equals(type));
        triggers.add(ot);
    }

    public boolean isTriggered(OrderTrigger.Type type, CurrencyRate rate){
        final Iterator<OrderTrigger> each = triggers.iterator();
        while (each.hasNext()) {
            OrderTrigger o = each.next();
            if (o.getType().equals(type)) {
                return isLevelCrossed(rate, o.getTrigger());
            }
        }
        return false;
    }

    private boolean isLevelCrossed(CurrencyRate currentRate, BigDecimal level){
        int a1 = currentRate.get().compareTo(level);
        int a2 = level.compareTo(currentRate.getPrev());
        return a1 * a2 == 1 || a1 == 0;
    }
}
