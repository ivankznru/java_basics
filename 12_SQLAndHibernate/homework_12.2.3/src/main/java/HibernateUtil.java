import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    static String hibernateConfig = "hibernate.cfg.xml";
    private static final SessionFactory sessionFactory;

    static {
        try {

            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure(hibernateConfig).build();
            Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
             sessionFactory = metadata.getSessionFactoryBuilder().build();
            
            
            
        } catch (Throwable e) {
            System.out.println("Error in creating SessionFactory object." + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
