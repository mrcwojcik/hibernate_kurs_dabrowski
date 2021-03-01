package pl.mrcwojcik.hibernate.lessons.batch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import pl.mrcwojcik.hibernate.App;
import pl.mrcwojcik.hibernate.entity.batch.BatchReview;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App25batchInsert {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.unwrap(Session.class).setJdbcBatchSize(10); // ustawianie batch size dla sesji
        em.getTransaction().begin();

        Long lastId = em.createQuery("SELECT max(r.id) FROM BatchReview r", Long.class).getSingleResult();

        for (long i = 1; i <= 25; i++){
            if (i % 5 == 0){
                em.flush(); // powoduje zapis do bd wszystkie niezapisane encje
                em.clear(); // czyści kontekst
            }
            em.persist(new BatchReview(lastId + i, "Treść id " + i, 5, 1L));
        }

        em.getTransaction().commit();
        em.close();
    }

}
