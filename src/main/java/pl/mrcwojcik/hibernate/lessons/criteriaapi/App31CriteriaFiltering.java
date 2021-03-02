package pl.mrcwojcik.hibernate.lessons.criteriaapi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mrcwojcik.hibernate.App;
import pl.mrcwojcik.hibernate.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.List;

public class App31CriteriaFiltering {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
        Join<Object, Object> orders = (Join<Object, Object>) customerRoot.fetch("orders", JoinType.INNER);
        orders.fetch("ordersRow")
                .fetch("product");
        ParameterExpression<Long> id = criteriaBuilder.parameter(Long.class);
        ParameterExpression<Long> id2 = criteriaBuilder.parameter(Long.class);
        ParameterExpression<BigDecimal> total = criteriaBuilder.parameter(BigDecimal.class);

        criteriaQuery.select(customerRoot).distinct(true)
                .where(
                        criteriaBuilder.and(
                                criteriaBuilder.or(
                                        criteriaBuilder.equal(customerRoot.get("id"), id),
                                        criteriaBuilder.equal(customerRoot.get("id"), id2)
                                ),
                                criteriaBuilder.between(orders.get("total"), total,
                                        criteriaBuilder.literal(new BigDecimal("70.00"))),
                                criteriaBuilder.isNotNull(customerRoot.get("firstname"))
                        )
                );

        TypedQuery<Customer> query = em.createQuery(criteriaQuery);
        query.setParameter(id, 1L);
        query.setParameter(id2, 2L);
        query.setParameter(total, new BigDecimal("30.00"));
        List<Customer> customers = query.getResultList();

        for (Customer customer : customers){
            logger.info(customer);
            logger.info(customer.getOrders());
        }

        em.getTransaction().commit();
        em.close();
    }

}
