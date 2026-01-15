package com.smart_expense_tracker.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ExpenseRequest {
	private String title;
    private double amount;
    private String category;
    private LocalDate expenseDate;

}
