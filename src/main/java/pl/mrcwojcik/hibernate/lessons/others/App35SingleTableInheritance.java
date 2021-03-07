package pl.mrcwojcik.hibernate.lessons.others;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mrcwojcik.hibernate.App;
import pl.mrcwojcik.hibernate.entity.product.RealProduct;
import pl.mrcwojcik.hibernate.entity.product.VirtualProduct;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class App35SingleTableInheritance {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        VirtualProduct virtualProduct = new VirtualProduct();
        virtualProduct.setName("Virtual");
        virtualProduct.setDescription("Desc v1");
        virtualProduct.setCreated(LocalDateTime.now());
        virtualProduct.setDownloable(true);
        virtualProduct.setFilePath("/store");
        virtualProduct.setFilePath("test.txt");
        em.persist(virtualProduct);

        RealProduct realProduct = new RealProduct();
        realProduct.setName("Real");
        realProduct.setDescription("Desc p1");
        realProduct.setCreated(LocalDateTime.now());
        realProduct.setHeight(100);
        realProduct.setWidth(100);
        realProduct.setWeight(10.5f);
        realProduct.setLength(100);
        em.persist(realProduct);

        em.flush();
        em.clear();

        logger.info(em.find(VirtualProduct.class, virtualProduct.getId()));
        logger.info(em.find(RealProduct.class, realProduct.getId()));


        em.getTransaction().commit();
        em.close();
    }

}
