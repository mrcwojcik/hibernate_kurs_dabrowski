package pl.mrcwojcik.hibernate.lessons.jqp_queries;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mrcwojcik.hibernate.App;
import pl.mrcwojcik.hibernate.entity.Customer;
import pl.mrcwojcik.hibernate.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class App21MultiMultiJoin {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        /*

        SELECT cu.id, cu.lastname customer, c.name category, SUM(orw.price) total FROM customer cu
        INNER JOIN `order` o on cu.id = o.customer_id
        INNER JOIN order_row orw on o.id = orw.order_id
        INNER JOIN product p on orw.product_id = p.id
        INNER JOIN category c on c.id = p.category_id
        GROUP BY c.id, cu.id
        HAVING total > 50
        ORDER BY total DESC;

         */

        Query query = em.createQuery(
                "SELECT DISTINCT c.id, c.lastname as customer, ca.name as category, SUM(orw.price) as total FROM Customer c " +
                        "INNER JOIN c.orders o " +
                        "INNER JOIN o.orderRows orw " +
                        "INNER JOIN orw.product p " +
                        "INNER JOIN p.category ca " +
                        "GROUP BY ca, c " +
                        "HAVING SUM(orw.price) > 50" +
                        "ORDER BY total desc");

        List<Object[]> resultList = query.getResultList();
        logger.info(resultList.size());
        for (Object[] row : resultList){
            logger.info(row[0] + ", \t" + row[1] + ", \t" + row[2] + ", \t" + row[3] );
        }

        em.getTransaction().commit();
        em.close();
    }


}
