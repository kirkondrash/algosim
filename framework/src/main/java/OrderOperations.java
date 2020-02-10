public interface OrderOperations<T extends Order> {
    T buyNow();
    T sellNow();
    T buyStop(double pips) throws TradingLogicException;
    T sellStop(double pips) throws TradingLogicException;
    T buyLimit(double pips) throws TradingLogicException;
    T sellLimit(double pips) throws TradingLogicException;
    T stopLoss(double pips) throws TradingLogicException;
    T makeProfit(double pips) throws TradingLogicException;
    T close();
}
