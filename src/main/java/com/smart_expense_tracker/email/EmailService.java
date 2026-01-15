package com.smart_expense_tracker.email;

public interface EmailService {
	void sendReport(String to, byte[] pdf);
}
