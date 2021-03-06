package pl.wolniarskim.expenses_tracker.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    LocalDate expenseDate;

    @Embedded
    Audit audit;

    @Embedded
    PaymentMethod paymentMethod;

    @Embedded
    ExpenseCategory expenseCategory;

    BigDecimal paymentAmount;

    String note;
}
