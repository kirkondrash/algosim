import java.sql.SQLException;

public interface TradingAlgorithm {
    void receiveTick(Tick tick) throws TradingLogicException, SQLException;
    void evaluateResult() throws SQLException;
}
