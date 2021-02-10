package pl.mrcwojcik.hibernate.crud;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mrcwojcik.hibernate.App;
import pl.mrcwojcik.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App03Update {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Product product = em.find(Product.class, 1L);
        product.setName("Nowy rower"); // Dirtychecking - mechanizm, który sprawdza, czy wartości w encji się zmieniły. Przy ponownym uruchomieniu sprawdzi, że Nowy Rower już istnieje
        Product merged = em.merge(product); // Dirtychecking działa nawet mimo metody merge

        logger.info(merged);
        em.getTransaction().commit();
        em.close();
    }

}
