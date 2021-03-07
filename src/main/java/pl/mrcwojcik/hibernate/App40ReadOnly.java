package pl.mrcwojcik.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.QueryHints;
import pl.mrcwojcik.hibernate.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App40ReadOnly {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Customer customer = em.createQuery("SELECT c FROM Customer c WHERE c.id=:id", Customer.class)
                .setParameter("id", 1L)
                .setHint(QueryHints.READ_ONLY, true) //tylko do odczytu, nie pozwoli na wprowadzanie żadnych zmian na danej encji
                .getSingleResult();
        logger.info(customer);

        em.getTransaction().commit();
        em.close();
    }

}
