import java.math.BigDecimal;

public interface OrderOperations<T> {
    T buyNow() throws TradingLogicException;
    T sellNow() throws TradingLogicException;
    T buyStop(BigDecimal pips) throws TradingLogicException;
    T sellStop(BigDecimal pips) throws TradingLogicException;
    T buyLimit(BigDecimal pips) throws TradingLogicException;
    T sellLimit(BigDecimal pips) throws TradingLogicException;
    T stopLoss(BigDecimal pips) throws TradingLogicException;
    T makeProfit(BigDecimal pips) throws TradingLogicException;
}
