package pl.wolniarskim.expenses_tracker.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Embeddable
public class Audit {

    LocalDate createdTime;

    @ManyToOne
    User recordOwner;
}
