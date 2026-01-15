package com.smart_expense_tracker.email;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

	private final JavaMailSender mailSender;

	@Override
	public void sendReport(String to, byte[] pdf) {
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);

			helper.setTo(to);
			helper.setSubject("Your Monthly Expense Report");
			helper.setText("Attached is your monthly expense report.");

			helper.addAttachment("report.pdf", new ByteArrayResource(pdf));
			mailSender.send(msg);
		} catch (Exception e) {
			throw new RuntimeException("Failed to send email", e);
		}

	}

}
