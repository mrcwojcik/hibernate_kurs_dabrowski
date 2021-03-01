package pl.mrcwojcik.hibernate.lessons.jqp_queries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mrcwojcik.hibernate.App;
import pl.mrcwojcik.hibernate.entity.ProductInCategoryCounterDto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class App17jpqlAutomaticResponseMapping {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("SELECT new pl.mrcwojcik.hibernate.entity.ProductInCategoryCounterDto(p.category.id, COUNT(p)) FROM Product p GROUP BY p.category");

        List<ProductInCategoryCounterDto> results = query.getResultList();
        for (ProductInCategoryCounterDto dto : results){
            logger.info("Category id: " + dto.getCategoryId());
            logger.info("Counter: " + dto.getProductInCategoryCounter());
        }

        em.getTransaction().commit();
        em.close();
    }

}
