package pl.mrcwojcik.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.mrcwojcik.hibernate.entity.Attribute;
import pl.mrcwojcik.hibernate.entity.Category;
import pl.mrcwojcik.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App11PersistRelations {

    private static Logger logger = LogManager.getLogger(App.class);
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        // OneToOne
//        Product product = em.find(Product.class, 3L);
//        Category category = new Category();
//        category.setName("Nowa kategoria");
//        category.setDescription("Opis nowej kategorii");
//        em.persist(category);
//        product.setCategory(category);

        // ManyToMany
        Product secondProduct = em.find(Product.class, 5L);
//        Attribute attribute = em.find(Attribute.class, 1L);
//        secondProduct.addAttribute(attribute);

        Attribute attribute1 = new Attribute();
        attribute1.setName("COLOR");
        attribute1.setValue("PINK");
        secondProduct.addAttribute(attribute1);


        em.getTransaction().commit();
        em.close();
    }

}
