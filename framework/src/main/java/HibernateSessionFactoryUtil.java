import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Order.class);
                configuration.addAnnotatedClass(OrderTrigger.class);
                configuration.setProperty("hibernate.connection.url", String.format("jdbc:postgresql://%s/%s?reWriteBatchedInserts=true",System.getProperty("postgres.hostport"), System.getProperty("postgres.database")));
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return sessionFactory;
    }
}