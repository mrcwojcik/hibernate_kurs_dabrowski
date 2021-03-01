package pl.mrcwojcik.hibernate.lessons.jqp_queries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mrcwojcik.hibernate.App;
import pl.mrcwojcik.hibernate.entity.Product;

import javax.persistence.*;
import java.util.List;

public class App15jpql {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

//        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.name LIKE '%04'", Product.class);
//        List<Product> productList = query.getResultList();
//        for (Product product : productList){
//            logger.info(product);
//        }

//        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.id=:id", Product.class);
//        query.setParameter("id", 100L);
//
//        try {
//            Product product = query.getSingleResult();
//            logger.info(product);
//        } catch (NoResultException e){
////            logger.error("Brak wyników", e);
//            throw new RuntimeException("Brak wyników", e);
//        }

        TypedQuery<Product> query2 = em.createQuery("SELECT p FROM Product p WHERE p.id=:id", Product.class);
        query2.setParameter("id", 3L);
        Product product2 = query2.getResultStream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Brak wyników"));

        logger.info(product2);

        Query query3 = em.createQuery("SELECT AVG(p.price) FROM Product p");
        Double singleResult = (Double) query3.getSingleResult();
        logger.info(singleResult);

        Query query4 = em.createQuery("SELECT COUNT(p), AVG (p.price) FROM Product p");
        Object[] results = (Object[]) query4.getSingleResult();
        logger.info(results[0] + ", " + results[1]);

        em.getTransaction().commit();
        em.close();
    }

}
