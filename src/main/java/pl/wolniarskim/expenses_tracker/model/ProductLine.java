package pl.wolniarskim.expenses_tracker.model;

import javax.persistence.*;

@Entity
@Table(name = "product_lines")
public class ProductLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    Expense expense;

    int quantity;
    double unitPrice;
    double totalPrice;
}
