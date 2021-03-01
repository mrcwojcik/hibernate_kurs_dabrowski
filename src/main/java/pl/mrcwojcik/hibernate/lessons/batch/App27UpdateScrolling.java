package pl.mrcwojcik.hibernate.lessons.batch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.annotations.QueryHints;
import pl.mrcwojcik.hibernate.App;
import pl.mrcwojcik.hibernate.entity.batch.BatchReview;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.stream.Stream;

public class App27UpdateScrolling {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        // możemy strumieniować tak dane z mysql wiersz po wierszu
        em.createQuery("SELECT r from BatchReview  r", BatchReview.class)
                .setHint(QueryHints.FETCH_SIZE, Integer.MIN_VALUE)
                .getResultStream()
                .forEach(batchReview -> {
                    batchReview.setContent("Tresc ze strumienia");
                    logger.info(batchReview);
                });

        em.getTransaction().commit();
        em.close();

    }

}
