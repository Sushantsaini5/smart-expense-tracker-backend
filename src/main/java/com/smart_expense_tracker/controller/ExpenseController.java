package com.smart_expense_tracker.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smart_expense_tracker.dto.ExpenseRequest;
import com.smart_expense_tracker.model.Expense;
import com.smart_expense_tracker.service.ExpenseService;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

	private final ExpenseService expenseService;

	public ExpenseController(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}

	@PostMapping("/addExpense")
	public Expense addExpense(@RequestBody ExpenseRequest request, Authentication authentication) {

		String userEmail = authentication.getName();

		return expenseService.addExpense(request, userEmail);
	}

	@GetMapping("/getExpense")
	public List<Expense> getUserExpenses(Authentication authentication) {

		String email = authentication.getName();
		return expenseService.getUserExpenses(email);

	}

	@PostMapping("/summary")
	public Double getMonthlySummary(@RequestParam int month, @RequestParam int year, Authentication authentication) {
		String email = authentication.getName();
		return expenseService.getMonthlyTotal(month, year, email);

	}

	@PostMapping("/category-summary")
	public Map<String, Double> getCategorySummary(@RequestParam int month, @RequestParam int year,
			Authentication authentication) {

		String email = authentication.getName();
		return expenseService.getCategorySummary(month, year, email);

	}

	@GetMapping("/trend/monthly")
	public Map<String, Double> monthlyTrend(Authentication auth) {
		return expenseService.getMonthlyTrend(auth.getName());
	}

	@GetMapping("/trend/categories")
	public Map<String, Double> topCategories(Authentication auth) {
		return expenseService.getTopCategories(auth.getName());
	}

	@GetMapping("/trend/daily")
	public Map<String, Double> dailyTrend(@RequestParam int month, @RequestParam int year, Authentication auth) {
		return expenseService.getDailyTrend(month, year, auth.getName());
	}

}
