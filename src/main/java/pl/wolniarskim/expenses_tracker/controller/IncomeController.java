package pl.wolniarskim.expenses_tracker.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wolniarskim.expenses_tracker.model.Expense;
import pl.wolniarskim.expenses_tracker.model.Income;
import pl.wolniarskim.expenses_tracker.service.IncomeService;

@RestController
@RequestMapping("api/incomes")
public class IncomeController {

    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping
    public Income createExpense(@RequestBody Income income){
        return incomeService.createExpense(income);
    }
}
