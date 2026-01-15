package com.smart_expense_tracker.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.smart_expense_tracker.dto.ExpenseRequest;
import com.smart_expense_tracker.model.Expense;
import com.smart_expense_tracker.repository.ExpenseRepository;
import com.smart_expense_tracker.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	private final ExpenseRepository expenseRepository;

	public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
		this.expenseRepository = expenseRepository;
	}

	@Override
	public Expense addExpense(ExpenseRequest request, String userEmail) {
		Expense expense = new Expense();
		expense.setTitle(request.getTitle());
		expense.setAmount(request.getAmount());
		expense.setCategory(request.getCategory());
		expense.setExpenseDate(request.getExpenseDate());
		expense.setUserEmail(userEmail);

		return expenseRepository.save(expense);
	}

	@Override
	public List<Expense> getUserExpenses(String userEmail) {
		return expenseRepository.findByUserEmail(userEmail);
	}

	@Override
	public Double getMonthlyTotal(int month, int year, String email) {
		Double total = expenseRepository.getMonthlyTotal(month, year, email);
		return total != null ? total : 0.0;
	}

	@Override
	public Map<String, Double> getCategorySummary(int month, int year, String email) {
		// TODO Auto-generated method stub
		List<Object[]> data = expenseRepository.getCategorySummary(month, year, email);

		Map<String, Double> result = new HashMap<>();

		for (Object[] row : data) {
			String category = (String) row[0];
			Double total = (Double) row[1];
			result.put(category, total);
		}
		return result;
	}

	@Override
	public Map<String, Double> getMonthlyTrend(String email) {
		Map<String, Double> map = new LinkedHashMap<>();
		for (Object[] row : expenseRepository.getMonthlyTrend(email)) {
			map.put("Month " + row[0], (Double) row[1]);
		}
		return map;
	}

	@Override
	public Map<String, Double> getTopCategories(String email) {
		Map<String, Double> map = new LinkedHashMap<>();
		for (Object[] row : expenseRepository.getTopCategories(email)) {
			map.put((String) row[0], (Double) row[1]);
		}
		return map;
	}

	@Override
	public Map<String, Double> getDailyTrend(int month, int year, String email) {
		Map<String, Double> map = new LinkedHashMap<>();
		for (Object[] row : expenseRepository.getDailyTrend(month, year, email)) {
			map.put("Day " + row[0], (Double) row[1]);
		}
		return map;
	}

}
