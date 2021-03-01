package pl.mrcwojcik.hibernate.lessons.jqp_queries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mrcwojcik.hibernate.App;
import pl.mrcwojcik.hibernate.entity.Order;
import pl.mrcwojcik.hibernate.entity.OrderRow;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App23EntityGraph {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

//        EntityGraph entityGraph = em.getEntityGraph("order-rows");
        EntityGraph entityGraph = em.createEntityGraph(Order.class);
        entityGraph.addAttributeNodes("orderRows");
        entityGraph.addAttributeNodes("customer");

        Subgraph<OrderRow> orderRows = entityGraph.addSubgraph("orderRows");
        orderRows.addAttributeNodes("product");

//        Map<String, Object> map = new HashMap<>();
//        map.put("javax.persistence.fetchgraph", entityGraph);

//        Order order = em.find(Order.class, 1L, map);
        List<Order> orderList = em.createQuery("SELECT o FROM Order o", Order.class)
                .setHint("javax.persistence.fetchgraph", entityGraph)
                .getResultList();
        logger.info(orderList);

        for (Order order : orderList){
            for (OrderRow orderRow : order.getOrderRows()){
                logger.info(orderRow);
                logger.info(orderRow.getProduct());
            }
        }
//        logger.info(order.getCustomer());

        em.getTransaction().commit();
        em.close();
    }


}
