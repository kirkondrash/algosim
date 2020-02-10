public interface TradingAlgorithm {
    void receiveTick(double pips) throws TradingLogicException;
    void evaluateResult();
}
