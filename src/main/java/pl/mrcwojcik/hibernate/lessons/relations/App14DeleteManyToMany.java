package pl.mrcwojcik.hibernate.lessons.relations;

import pl.mrcwojcik.hibernate.entity.Attribute;
import pl.mrcwojcik.hibernate.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class App14DeleteManyToMany {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

//        Product product = em.find(Product.class, 5L);
////        em.remove(product);
//        product.getAttributes().clear();

        Attribute attribute = em.find(Attribute.class, 1L);
        // nie da się usunąć w taki sposób atrybutu, bo nie ma delete na powiązaniach
//        em.remove(attribute);
        for (Product product : new ArrayList<>(attribute.getProducts())){
            attribute.removeProduct(product);
        }
        em.remove(attribute);

        em.getTransaction().commit();
        em.close();
    }

}
