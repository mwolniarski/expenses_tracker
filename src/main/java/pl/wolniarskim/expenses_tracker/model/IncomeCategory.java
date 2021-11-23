package pl.wolniarskim.expenses_tracker.model;

import javax.persistence.Embeddable;

@Embeddable
public enum IncomeCategory {
    SALARY, BONUS, OTHER
}
