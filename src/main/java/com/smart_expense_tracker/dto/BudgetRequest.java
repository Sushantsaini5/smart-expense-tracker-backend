package com.smart_expense_tracker.dto;

import lombok.Data;

@Data
public class BudgetRequest {
    private Double monthlyLimit;
    private int month;
    private int year;
}

