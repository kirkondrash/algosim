import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgreSQLManager {
    private static final String url = String.format("jdbc:postgresql://%s/%s?reWriteBatchedInserts=true",System.getProperty("postgres.hostport"), System.getProperty("postgres.database"));
    private static final String user = System.getProperty("postgres.username");
    private static final String password = System.getProperty("postgres.password");
    private static Properties queries = null;


    public static Connection getConnection() throws SQLException {
        Connection con =  DriverManager.getConnection(url, user, password);
        con.setAutoCommit(false);
        return con;
    }

    public static Properties getQueries() {
        if (queries==null){
            queries = new Properties();
            try (InputStream inputStream = ClassLoader.getSystemResourceAsStream("order-queries.conf")) {
                queries.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }
        return queries;
    }
}
