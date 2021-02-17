package pl.mrcwojcik.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.QueryHints;
import pl.mrcwojcik.hibernate.entity.Product;

import javax.persistence.*;
import java.util.List;

public class App20CartesianProduct {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        // Problem kartezjanski
//        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p LEFT JOIN FETCH p.attributes LEFT JOIN FETCH p.reviewList", Product.class);
//        List<Product> productList = query.getResultList();
//        logger.info("Size: " + productList.size());
//        for (Product p : productList){
//            logger.info(p);
//            logger.info(p.getAttributes());
//            logger.info(p.getReviewList());
//        }

        List<Product> productList = em.createQuery("SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.attributes", Product.class)
                .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                .getResultList();
        productList = em.createQuery("SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.reviewList", Product.class)
                .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
                .getResultList();
        logger.info("Size: " + productList.size());
        for (Product p : productList){
            logger.info(p);
            logger.info(p.getAttributes());
            logger.info(p.getReviewList());
        }

        em.getTransaction().commit();
        em.close();
    }

}
