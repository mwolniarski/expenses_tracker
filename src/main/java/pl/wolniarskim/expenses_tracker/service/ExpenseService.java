package pl.wolniarskim.expenses_tracker.service;

import org.springframework.stereotype.Service;
import pl.wolniarskim.expenses_tracker.model.Expense;
import pl.wolniarskim.expenses_tracker.repository.ExpenseRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class ExpenseService {

    ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense createExpense(Expense expense){
        Expense returnedExpense = expenseRepository.save(expense);
        return returnedExpense;
    }

    public void deleteExpense(long id){
        if(!expenseRepository.existsById(id))
            throw new EntityNotFoundException();
        expenseRepository.deleteById(id);
    }
}
