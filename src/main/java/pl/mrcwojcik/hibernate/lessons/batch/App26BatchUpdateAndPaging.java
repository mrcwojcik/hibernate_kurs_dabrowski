package pl.mrcwojcik.hibernate.lessons.batch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import pl.mrcwojcik.hibernate.App;
import pl.mrcwojcik.hibernate.entity.batch.BatchReview;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App26BatchUpdateAndPaging {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Long count = em.createQuery("SELECT count(r) FROM BatchReview  r", Long.class).getSingleResult();
        int batchSize = 10;
        em.unwrap(Session.class).setJdbcBatchSize(batchSize);
        for (int i = 0; i < count; i = i + batchSize){
            List<BatchReview> review = em.createQuery("SELECT r FROM BatchReview r", BatchReview.class)
                    .setFirstResult(i)
                    .setMaxResults(5)
                    .getResultList();
            logger.info(review);
            for (BatchReview batchReview : review){
                batchReview.setContent("Nowa tresc!!!");
                batchReview.setRating(11);
            }

            em.flush();
            em.clear();
        }

        em.getTransaction().commit();
        em.close();
    }

}
