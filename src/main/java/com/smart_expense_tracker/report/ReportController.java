package com.smart_expense_tracker.report;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smart_expense_tracker.email.EmailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {
	private final ReportService reportService;
	private final EmailService emailService;

	@PostMapping("/send")
	public String sendReport(@RequestParam int month, @RequestParam int year, Authentication auth) {

		String email = auth.getName();
		byte[] pdf = reportService.generateMonthlyReport(month, year, email);
		emailService.sendReport(email, pdf);

		return "Report sent successfully";
	}

}
