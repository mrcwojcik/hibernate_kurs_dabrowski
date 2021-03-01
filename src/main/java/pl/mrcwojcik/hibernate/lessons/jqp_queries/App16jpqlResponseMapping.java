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
import java.util.stream.Collectors;

public class App16jpqlResponseMapping {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("SELECT p.category.id, COUNT(p) FROM Product p GROUP BY p.category");
        List<Object[]> resultList = query.getResultList();
//        for (Object[] array : resultList){
//            logger.info(array[0] + ", " + array[1]);
//        }

        List<ProductInCategoryCounterDto> result = resultList.stream()
                .map(objects -> new ProductInCategoryCounterDto((Long) objects[0], (Long) objects[1]))
                .collect(Collectors.toList());

        for (ProductInCategoryCounterDto dto : result){
            logger.info("Category id: " + dto.getCategoryId());
            logger.info("Counter: " + dto.getProductInCategoryCounter());
        }

        em.getTransaction().commit();
        em.close();
    }

}
