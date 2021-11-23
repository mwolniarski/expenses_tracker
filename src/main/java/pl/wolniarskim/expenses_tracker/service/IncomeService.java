package pl.wolniarskim.expenses_tracker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wolniarskim.expenses_tracker.model.Expense;
import pl.wolniarskim.expenses_tracker.model.Income;
import pl.wolniarskim.expenses_tracker.repository.ExpenseRepository;
import pl.wolniarskim.expenses_tracker.repository.IncomeRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class IncomeService {

    private final IncomeRepository incomeRepository;

    private Logger logger = LoggerFactory.getLogger("Income service");

    public IncomeService(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public Income createExpense(Income income){
        Income returnedIncome = incomeRepository.save(income);
        return returnedIncome;
    }
}
