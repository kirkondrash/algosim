import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.time.Instant;

public abstract class TradingAlgorithm {
    private static Logger log = LogManager.getLogger(TradingAlgorithm.class);
    OrdersDAO ordersBase;
    CurrencyRates currencyRates;
    HttpClient httpClient = HttpClient.newBuilder().authenticator(new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(System.getenv("API_CLIENT_USER"),System.getenv("API_CLIENT_PASSWORD").toCharArray());
        }
    }).followRedirects(HttpClient.Redirect.NORMAL).build();
    String recommendationBasePath;

    public TradingAlgorithm() throws SQLException {
        if (System.getProperty("framework.debug")!=null){
            Configurator.setLevel("TradingAlgorithm", Level.DEBUG);
        }
        currencyRates = new CurrencyRates();
        ordersBase = new OrdersDAO(currencyRates);
        recommendationBasePath = System.getProperty("recommendation.basePath");
    }

    public void receiveTick(Tick tick) throws TradingLogicException, SQLException {
        CurrencyRate currencyRate = currencyRates.updateAndReturnCurrentRate(tick.getCurrencyPair(),tick.getRate());
        log.debug(String.format("%s - %s: %s",
                Instant.ofEpochSecond(tick.getTimestamp()).toString(),
                tick.getCurrencyPair(),
                currencyRate.toString()));

        handleTick(tick, currencyRate);

        ordersBase.executeOrders(tick.getCurrencyPair());
    }

    public abstract void handleTick(Tick tick, CurrencyRate currencyRate) throws SQLException;

    public void evaluateResult() throws SQLException {
        System.out.println(ordersBase.evaluateWinLoss());
        ordersBase.clearDB();
    }

    public boolean getBoolRecommendation(String modelName)  {
        try {
            HttpResponse<String> response = httpClient.send(
                    HttpRequest
                            .newBuilder()
                            .uri(URI.create(recommendationBasePath+"?modelName="+modelName+"&algoId="+System.getProperty("framework.algo_id")))
                            .GET()
                            .build(),
                    HttpResponse.BodyHandlers.ofString()
            );
            if (response.statusCode()==404){
                throw new RuntimeException("No model with specified id found!");
            }
            return Boolean.parseBoolean(response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
