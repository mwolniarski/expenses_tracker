package pl.wolniarskim.expenses_tracker.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Embeddable
public enum ExpenseCategory {
    FOOD, GIFT, OTHER, EDUCATION
}
