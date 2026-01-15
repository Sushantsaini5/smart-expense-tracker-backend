package com.smart_expense_tracker.report;

public interface ReportService {
	byte[] generateMonthlyReport(int month, int year, String email);
}
