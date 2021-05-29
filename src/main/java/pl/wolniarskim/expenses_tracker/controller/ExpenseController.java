package pl.wolniarskim.expenses_tracker.controller;

import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.wolniarskim.expenses_tracker.model.Expense;
import pl.wolniarskim.expenses_tracker.service.ExpenseService;

import java.io.IOException;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private ExpenseService expenseService;

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

    @PostMapping("/uploadFile")
    public String tesseractTest(@RequestParam("file") MultipartFile file) throws IOException, TesseractException {
        return expenseService.uploadFile(file);
    }
}
