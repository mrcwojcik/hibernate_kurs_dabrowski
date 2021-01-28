package pl.mrcwojcik.hibernate.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
//@Table - możemy wskazać, jak zapiszemy encję w tabeli
public class Product {

    /*
    Brak generacji - generacja ręczna identyfikatora
    Auto - Hibernate sam wybiera strategie w zależności od bazy danych
    Indetity - tworzy id na podstawie kolumny w bazie danych. W naszym przypadku to primary key, od razu daje AI
    Sequence - podobne ustawienie, ale przeznaczone dla baz wspierających sekwencje
    Table - pobiera klucz z bazy danych. Najmniej wydajne - kolejne zapytanie
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDateTime created;
    private LocalDateTime updated;
    private BigDecimal price;

    @Enumerated(EnumType.STRING) // mapowanie enuma
    @Column(name = "type")
    private ProductType productType;

    @OneToMany (mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Review> reviewList;

    // Hibernate daje dwie możliwości. Umieszczanie adnotacji na polach, ale też na getterach i setterach. Trzeba wybrać tylko jedną formę.


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", price=" + price +
                ", productType=" + productType +
                '}';
    }
}
