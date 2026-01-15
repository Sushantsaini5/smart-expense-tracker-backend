package com.smart_expense_tracker.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smart_expense_tracker.dto.BudgetRequest;
import com.smart_expense_tracker.model.Budget;
import com.smart_expense_tracker.service.BudgetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping("/set")
    public Budget setBudget(@RequestBody BudgetRequest request, Authentication auth) {
        return budgetService.setBudget(request, auth.getName());
    }

    @GetMapping("/status")
    public String getStatus(@RequestParam int month, @RequestParam int year, Authentication auth) {
        return budgetService.checkBudgetStatus(auth.getName(), month, year);
    }
}
