package pl.mrcwojcik.hibernate.lessons.batch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mrcwojcik.hibernate.App;
import pl.mrcwojcik.hibernate.entity.batch.BatchReview;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App29BatchDelete {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<BatchReview> reviewList = em.createQuery("SELECT r FROM BatchReview r WHERE r.productId=:id", BatchReview.class)
                .setParameter("id", 1L)
                .getResultList();

        for (BatchReview batchReview : reviewList){
            logger.info(batchReview);
            em.remove(batchReview);
        }

        em.getTransaction().commit();
        em.close();
    }

}
