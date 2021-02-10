package pl.mrcwojcik.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mrcwojcik.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class App18Join {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        // w jpql nie musi być słowa kluczowego ON
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p INNER JOIN FETCH p.category c WHERE c.id =:id", Product.class);
        query.setParameter("id", 1L);

        List<Product> productList = query.getResultList();

        for (Product p : productList){
            logger.info(p);
            logger.info(p.getCategory());
        }


        em.getTransaction().commit();
        em.close();
    }

}
