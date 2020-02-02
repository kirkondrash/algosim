public interface OrderOperations<T extends Order> {
    T buyNow();
    T sellNow();
    T buyStop(int pips) throws TradingLogicException;
    T sellStop(int pips) throws TradingLogicException;
    T buyLimit(int pips) throws TradingLogicException;
    T sellLimit(int pips) throws TradingLogicException;
    T stopLoss(int pips) throws TradingLogicException;
    T makeProfit(int pips) throws TradingLogicException;
    T close();
}
