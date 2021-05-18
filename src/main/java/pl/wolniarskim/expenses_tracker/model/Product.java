package pl.wolniarskim.expenses_tracker.model;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "category_name")
    Category category;

    String productName;
    String description;
}
