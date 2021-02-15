package pl.mrcwojcik.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mrcwojcik.hibernate.entity.Category;
import pl.mrcwojcik.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class App19MultiJoin {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c LEFT JOIN FETCH c.product p LEFT JOIN FETCH p.reviewList WHERE c.id=:id", Category.class);
        query.setParameter("id", 1L);
        List<Category> resultList = query.getResultList();

        for (Category c: resultList){
            logger.info(c);
            for (Product p : c.getProduct()){
                logger.info(p.getReviewList());
            }
        }

        em.getTransaction().commit();
        em.close();
    }

}
