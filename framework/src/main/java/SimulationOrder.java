import java.math.BigDecimal;

public class SimulationOrder extends Order implements OrderOperations<SimulationOrder>{

    enum State {WAIT, OPENED, CLOSED}
    enum Type {BUY, SELL}

    private State state;
    private Type type;
    private CurrencyRate currencyRate;
    private BigDecimal stopLossPrice;
    private BigDecimal makeProfitPrice;

    public SimulationOrder(String pair, CurrencyRate currencyRate){
        state = State.WAIT;
        this.currencyRate = currencyRate;
        this.pair = pair;
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

    public CurrencyRate getCurrencyRate() {
        return currencyRate;
    }

    public BigDecimal getStopLossPrice() {
        return stopLossPrice;
    }

    public BigDecimal getMakeProfitPrice() {
        return makeProfitPrice;
    }

    public SimulationOrder lot(int lot) {
        this.lot = lot;
        return this;
    }

    public SimulationOrder buyNow() throws TradingLogicException {
        if (state != State.WAIT)
            throw new TradingLogicException("Wrong move! Buy can be applied only to not opened orders!");
        type =  Type.BUY;
        openingPrice = currencyRate.get();
        return this;
    }

    public SimulationOrder sellNow() throws TradingLogicException {
        if (state != State.WAIT)
            throw new TradingLogicException("Wrong move! Sell can be applied only to not opened orders!");
        type = Type.SELL;
        openingPrice = currencyRate.get();
        return this;
    }

    public SimulationOrder buyStop(BigDecimal pips) throws TradingLogicException {
        if (state != State.WAIT)
            throw new TradingLogicException("Wrong move! BuyStop can be applied only to not opened orders!");
        if (pips.compareTo(currencyRate.get()) != 1)
            throw new TradingLogicException(String.format(
                    "Wrong move! BuyStop level [%.5f] has to be higher than current[%.5f]!",
                    pips,
                    currencyRate.get()));
        type = Type.BUY;
        openingPrice = pips;
        return this;
    }

    public SimulationOrder sellStop(BigDecimal pips) throws TradingLogicException {
        if (state != State.WAIT)
            throw new TradingLogicException("Wrong move! SellStop can be applied only to not opened orders!");
        if (pips.compareTo(currencyRate.get()) != -1)
            throw new TradingLogicException(String.format(
                    "Wrong move! SellStop level [%.5f] has to be lower than current[%.5f]!",
                    pips,
                    currencyRate.get()));
        type = Type.SELL;
        openingPrice = pips;
        return this;
    }

    public SimulationOrder buyLimit(BigDecimal pips) throws TradingLogicException {
        if (state != State.WAIT)
            throw new TradingLogicException("Wrong move! BuyLimit can be applied only to not opened orders!");
        if (pips.compareTo(currencyRate.get()) != -1)
            throw new TradingLogicException(String.format(
                    "Wrong move! BuyLimit level [%.5f] has to be lower than current[%.5f]!",
                    pips,
                    currencyRate.get()));
        type = Type.BUY;
        openingPrice = pips;
        return this;
    }

    public SimulationOrder sellLimit(BigDecimal pips) throws TradingLogicException {
        if (state != State.WAIT)
            throw new TradingLogicException("Wrong move! SellLimit can be applied only to not opened orders!");
        if (pips.compareTo(currencyRate.get()) != 1)
            throw new TradingLogicException(String.format(
                    "Wrong move! SellLimit level [%.5f] has to be higher than current[%.5f]!",
                    pips,
                    currencyRate.get()));
        type = SimulationOrder.Type.SELL;
        openingPrice = pips;
        return this;
    }

    public SimulationOrder stopLoss(BigDecimal pips) throws TradingLogicException {
        switch (type){
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
        stopLossPrice = pips;
        return this;
    }

    public SimulationOrder makeProfit(BigDecimal pips) throws TradingLogicException {
        switch (type){
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
        makeProfitPrice = pips;
        return this;
    }

    public SimulationOrder close() {
        closingPrice = currencyRate.get();
        state = State.CLOSED;
        return this;
    }

    public SimulationOrder open() {
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
                        "stopLossPrice=%.5f" + stopLossPrice +
                        "makeProfitPrice=%5f" + makeProfitPrice +
                        "closingPrice=%s}",
                state,
                type,
                pair,
                lot,
                openingPrice,
                stopLossPrice,
                makeProfitPrice,
                closingPrice);
    }
}
