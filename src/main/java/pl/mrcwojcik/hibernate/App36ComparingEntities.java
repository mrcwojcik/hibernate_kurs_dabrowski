package pl.mrcwojcik.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mrcwojcik.hibernate.entity.Customer;
import pl.mrcwojcik.hibernate.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App36ComparingEntities {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Customer customer = em.find(Customer.class, 1L);
        logger.info(customer.getOrders());

        em.clear();

        //metoda porównania, np. metoda klucza biznesowego, np. numer pesel lub isbn dla książek. Pola muszą być niezmenne lub bardzo rzadko zmienione.

        Order order = em.find(Order.class, 4L);
        logger.info(order.equals(getOrder(customer, 4L)));

        em.getTransaction().commit();
        em.close();
    }

    private static Order getOrder(Customer customer, long id) {
        return customer.getOrders().stream()
                .filter(o -> o.getId()
                        .equals(id))
                .findFirst()
                .orElseThrow();
    }

}
