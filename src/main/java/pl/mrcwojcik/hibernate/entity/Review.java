package pl.mrcwojcik.hibernate.entity;

import javax.persistence.*;

@Entity
public class Review implements Comparable<Review> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private int rating;

    @ManyToOne (fetch = FetchType.LAZY) // relacja dwukierunkowa tylko wtedy, gdy jest naprawdę potrzebna
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int compareTo(Review o) {
        return id.compareTo(o.id);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                '}';
    }
}
