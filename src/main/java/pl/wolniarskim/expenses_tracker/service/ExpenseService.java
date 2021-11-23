package pl.wolniarskim.expenses_tracker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wolniarskim.expenses_tracker.model.Expense;
import pl.wolniarskim.expenses_tracker.repository.ExpenseRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    private Logger logger = LoggerFactory.getLogger("Expense service");

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense createExpense(Expense expense) {
        Expense returnedExpense = expenseRepository.save(expense);
        logger.info("Added:" + returnedExpense);
        return returnedExpense;
    }

    public List<Expense> getAll() {
        List<Expense> expenseList = expenseRepository.findAll();
        return expenseList;
    }
}
