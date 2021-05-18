package pl.wolniarskim.expenses_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wolniarskim.expenses_tracker.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
}
