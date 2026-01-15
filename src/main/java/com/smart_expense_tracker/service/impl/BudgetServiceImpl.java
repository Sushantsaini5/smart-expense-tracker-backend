package com.smart_expense_tracker.service.impl;

import org.springframework.stereotype.Service;

import com.smart_expense_tracker.dto.BudgetRequest;
import com.smart_expense_tracker.model.Budget;
import com.smart_expense_tracker.repository.BudgetRepository;
import com.smart_expense_tracker.repository.ExpenseRepository;
import com.smart_expense_tracker.service.BudgetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {
	
	private final BudgetRepository budgetRepo;
    private final ExpenseRepository expenseRepo;

	@Override
	public Budget setBudget(BudgetRequest request, String email) {

		Budget budget= budgetRepo.findByUserEmailAndMonthAndYear(email, request.getMonth(), request.getYear())
				.orElse(new Budget());
		
		budget.setUserEmail(email);
        budget.setMonthlyLimit(request.getMonthlyLimit());
        budget.setMonth(request.getMonth());
        budget.setYear(request.getYear());
        
		return budgetRepo.save(budget);
	}

	@Override
	public String checkBudgetStatus(String email, int month, int year) {
		
		Budget budget = budgetRepo
                .findByUserEmailAndMonthAndYear(email, month, year)
                .orElseThrow();
		Double totalSpent = expenseRepo.getMonthlyTotal(month, year, email);

        double percent = (totalSpent / budget.getMonthlyLimit()) * 100;

        if (percent >= 100) return "🚨 Budget Exceeded!";
        if (percent >= 80) return "⚠ Approaching Budget Limit";
        return "✅ Within Budget"; 
	}

}
