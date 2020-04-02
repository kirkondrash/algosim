public interface TradingAlgorithm {
    void receiveTick(Tick tick) throws TradingLogicException;
    void evaluateResult();
}
