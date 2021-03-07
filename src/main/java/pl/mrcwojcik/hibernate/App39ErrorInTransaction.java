package pl.mrcwojcik.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mrcwojcik.hibernate.entity.Customer;
import pl.mrcwojcik.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App39ErrorInTransaction {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        try{
            Customer customer = em.createQuery("SELECT c FROM Customer c WHERE c.id=:id", Customer.class)
                    .setParameter("id", 99L)
                    .getSingleResult();
            logger.info(customer);

            Product product = em.find(Product.class, 1L);
            logger.info(product);
            em.getTransaction().commit();
        } catch (Exception e){
            logger.info(em.getTransaction().isActive());
            logger.info(em.getTransaction().getRollbackOnly());
            if (em.getTransaction().isActive() || em.getTransaction().getRollbackOnly()){
                em.getTransaction().rollback();
                logger.error("Rollback transakcji", e);
            }
           logger.error(e);
        }

        em.close();
    }

}
