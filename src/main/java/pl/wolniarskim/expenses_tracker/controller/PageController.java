package pl.wolniarskim.expenses_tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.wolniarskim.expenses_tracker.model.Expense;
import pl.wolniarskim.expenses_tracker.service.ExpenseService;

@Controller
public class PageController {

    ExpenseService expenseService;

    public PageController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public String getMainPage(){
        return "index.html";
    }
}
