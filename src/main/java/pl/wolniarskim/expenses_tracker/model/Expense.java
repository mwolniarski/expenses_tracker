package pl.wolniarskim.expenses_tracker.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    LocalDate purchaseDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    double paymentAmount;
}
