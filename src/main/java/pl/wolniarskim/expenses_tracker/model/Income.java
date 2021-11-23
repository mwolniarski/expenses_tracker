package pl.wolniarskim.expenses_tracker.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "incomes")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    LocalDate incomeDate;

    @Embedded
    Audit audit;

    @Embedded
    IncomeCategory incomeCategory;

    @Embedded
    PaymentMethod paymentMethod;

    BigDecimal paymentAmount;

    String note;
}
