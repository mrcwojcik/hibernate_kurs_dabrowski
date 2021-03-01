package pl.mrcwojcik.hibernate.lessons.relations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mrcwojcik.hibernate.App;
import pl.mrcwojcik.hibernate.entity.Attribute;
import pl.mrcwojcik.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App10ManyToMany {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Product product = em.find(Product.class, 3L);
        logger.info(product);
        logger.info(product.getAttributes());

        Attribute attribute = em.find(Attribute.class, 1L);
        logger.info(attribute);
        logger.info(attribute.getProducts());

        em.getTransaction().commit();
        em.close();
    }

}
