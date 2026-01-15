package com.smart_expense_tracker.service;

import com.smart_expense_tracker.dto.BudgetRequest;
import com.smart_expense_tracker.model.Budget;

public interface BudgetService {
    Budget setBudget(BudgetRequest request, String email);
    String checkBudgetStatus(String email, int month, int year);
}
