package pl.mrcwojcik.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mrcwojcik.hibernate.entity.Customer;
import pl.mrcwojcik.hibernate.entity.Product;
import pl.mrcwojcik.hibernate.entity.Review;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App37SortingAndOrdering {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

//        Product product = em.find(Product.class, 1L);
//        Review review = new Review();
//        review.setContent("Content x1");
//        review.setRating(5);
//        review.setProduct(product);
//
//        Review review2 = new Review();
//        review2.setContent("Content x2");
//        review2.setRating(4);
//        review2.setProduct(product);
//
//        Review review3 = new Review();
//        review3.setContent("Content x3");
//        review3.setRating(5);
//        review3.setProduct(product);
//
//        Customer customer = em.find(Customer.class, 1L);
//        customer.getReviews().add(review);
//        customer.getReviews().add(review2);
//        customer.getReviews().add(review3);

        Customer customer = em.createQuery(
                "SELECT c FROM Customer c INNER JOIN FETCH c.reviews r WHERE c.id=:id ORDER BY r.id desc ", Customer.class)
                .setParameter("id", 1L)
                .getSingleResult();

        customer.getReviews().forEach(review ->{
            logger.info(review);
        });

        em.getTransaction().commit();
        em.close();
    }

}
