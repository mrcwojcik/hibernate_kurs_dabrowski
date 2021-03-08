package pl.mrcwojcik.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.jpa.QueryHints;
import pl.mrcwojcik.hibernate.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.QueryHint;
import java.util.List;

public class App43QueryCache {

    private static Logger logger = LogManager.getLogger(App42CollectionCache.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Customer> customers = em.createQuery("SELECT c FROM Customer c", Customer.class)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .getResultList();
        logger.info(customers);

        em.getTransaction().commit();
        em.close();

        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        customers = em.createQuery("SELECT c FROM Customer c", Customer.class)
                .setHint(QueryHints.HINT_CACHEABLE, true)
                .getResultList();
        logger.info(customers);

        em.getTransaction().commit();
        em.close();
    }

}
