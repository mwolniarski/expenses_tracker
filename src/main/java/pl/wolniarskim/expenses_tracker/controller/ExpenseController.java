package pl.wolniarskim.expenses_tracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.wolniarskim.expenses_tracker.model.Expense;
import pl.wolniarskim.expenses_tracker.service.ExpenseService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense){
        return ResponseEntity.ok(expenseService.createExpense(expense));
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses(){
        return ResponseEntity.ok(expenseService.getAll());
    }
}
