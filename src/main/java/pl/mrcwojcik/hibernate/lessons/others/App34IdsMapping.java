package pl.mrcwojcik.hibernate.lessons.others;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mrcwojcik.hibernate.App;
import pl.mrcwojcik.hibernate.entity.Customer;
import pl.mrcwojcik.hibernate.entity.CustomerDetails;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class App34IdsMapping {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Customer customer = em.find(Customer.class, 1L);
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setBirthPlace("Olsztyn");
        customerDetails.setFatherName("Adam");
        customerDetails.setMotherName("Iwona");
        customerDetails.setBirthday(LocalDate.of(2000, 10, 22));
        customerDetails.setPesel("89898999888");
        customerDetails.setCustomer(customer);
        customer.setCustomerDetails(customerDetails);

        em.persist(customer);

        logger.info(customer.getCustomerDetails());

        em.getTransaction().commit();
        em.close();
    }

}
