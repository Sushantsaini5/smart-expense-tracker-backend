package com.smart_expense_tracker.report;

import java.time.LocalDate;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.smart_expense_tracker.email.EmailService;
import com.smart_expense_tracker.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReportScheduler {

	private final ReportService reportService;
	private final EmailService emailService;
	private final UserRepository userRepository;

	// Runs at 9:00 AM on the 1st of every month
	@Scheduled(cron = "0 0 9 1 * ?")
	public void sendMonthlyReports() {

		int month = LocalDate.now().minusMonths(1).getMonthValue();
		int year = LocalDate.now().minusMonths(1).getYear();

		userRepository.findAll().forEach(user -> {

			byte[] pdf = reportService.generateMonthlyReport(month, year, user.getEmail());

			emailService.sendReport(user.getEmail(), pdf);
		});

		System.out.println("Monthly reports sent successfully.");
	}
}
