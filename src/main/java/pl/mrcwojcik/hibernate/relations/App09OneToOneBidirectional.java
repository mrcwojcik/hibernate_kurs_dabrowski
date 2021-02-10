package pl.mrcwojcik.hibernate.relations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mrcwojcik.hibernate.App;
import pl.mrcwojcik.hibernate.entity.Category;
import pl.mrcwojcik.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App09OneToOneBidirectional {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Category category = em.find(Category.class, 1L);
        logger.info(category);
        logger.info(category.getProduct());

        em.getTransaction().commit();
        em.close();
    }

}
