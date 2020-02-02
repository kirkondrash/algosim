public interface TradingAlgorithm {
    void receiveTick(int pips) throws TradingLogicException;
    void evaluateResult();
}
