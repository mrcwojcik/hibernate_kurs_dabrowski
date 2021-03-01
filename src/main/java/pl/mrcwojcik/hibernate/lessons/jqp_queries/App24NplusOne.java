package pl.mrcwojcik.hibernate.lessons.jqp_queries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mrcwojcik.hibernate.App;
import pl.mrcwojcik.hibernate.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App24NplusOne {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        List<Order> orders = em.createQuery("SELECT distinct o FROM Order o " +
                "inner join fetch o.orderRows", Order.class)
                .getResultList();

        logger.info("Ilość zamówień: " + orders.size());
        for (Order order : orders){
            logger.info(order.getId());
            logger.info(order.getOrderRows());
        }

        em.getTransaction().commit();
        em.close();
    }
}
