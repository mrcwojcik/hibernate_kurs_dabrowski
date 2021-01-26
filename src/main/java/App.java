import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager(); // EntityManager odpowiednik sesji dla JPA
        em.getTransaction().begin();
        logger.info("Hello!");
        em.getTransaction().commit();
        em.close();
    }

}