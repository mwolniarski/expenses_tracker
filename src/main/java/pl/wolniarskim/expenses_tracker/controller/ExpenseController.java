package pl.wolniarskim.expenses_tracker.controller;

import org.springframework.web.bind.annotation.*;
import pl.wolniarskim.expenses_tracker.model.Expense;
import pl.wolniarskim.expenses_tracker.service.ExpenseService;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public Expense createExpense(@RequestBody Expense expense){
        return expenseService.createExpense(expense);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable("id") long id){
        expenseService.deleteExpense(id);
    }
}
