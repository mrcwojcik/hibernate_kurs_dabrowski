package pl.mrcwojcik.hibernate.lessons.others;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mrcwojcik.hibernate.App;
import pl.mrcwojcik.hibernate.entity.Address;
import pl.mrcwojcik.hibernate.entity.AddressType;
import pl.mrcwojcik.hibernate.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Arrays;

public class App33ElementCollection {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Customer customer = new Customer();
        customer.setFirstname("Customer 1");
        customer.setLastname("lastname 1");
        customer.setCreated(LocalDateTime.now());
        customer.setUpdated(LocalDateTime.now());
        customer.setAddress(Arrays.asList(
                new Address(AddressType.BILLING, "Polna", "00-000", "Warszawa"),
                new Address(AddressType.SHIPPING, "Kanarkowa", "10-685", "Olsztyn"),
                new Address(AddressType.INVOICE, "Zielona", "10-000", "Poznań")
        ));
        em.persist(customer);
        em.flush();
        em.clear();

        Customer customer1 = em.find(Customer.class, customer.getId());
        logger.info(customer1);
        logger.info(customer1.getAddress());

        em.getTransaction().commit();
        em.close();
    }

}
