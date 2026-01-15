package com.smart_expense_tracker.service;

import java.util.List;
import java.util.Map;

import com.smart_expense_tracker.dto.ExpenseRequest;
import com.smart_expense_tracker.model.Expense;

public interface ExpenseService {
	Expense addExpense(ExpenseRequest request, String userEmail);

	List<Expense> getUserExpenses(String userEmail);

	Double getMonthlyTotal(int month, int year, String email);

	Map<String, Double> getCategorySummary(int month, int year, String email);

	Map<String, Double> getMonthlyTrend(String email);

	Map<String, Double> getTopCategories(String email);

	Map<String, Double> getDailyTrend(int month, int year, String email);

}
