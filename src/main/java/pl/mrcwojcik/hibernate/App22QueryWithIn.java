package pl.mrcwojcik.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mrcwojcik.hibernate.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

public class App22QueryWithIn {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Order> orderList = em.createQuery("SELECT o FROM Order o WHERE o.id in (:ids)", Order.class)
                .setParameter("ids", Arrays.asList(1L, 3L, 5L))
                .getResultList();

        // możemy stosować "in", ale też not in, czyli inne niż wskazane

        for (Order o : orderList){
            logger.info(o);
        }

        em.getTransaction().commit();
        em.close();
    }

}
