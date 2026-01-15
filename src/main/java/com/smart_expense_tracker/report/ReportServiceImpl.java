package com.smart_expense_tracker.report;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.smart_expense_tracker.model.Expense;
import com.smart_expense_tracker.repository.ExpenseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

	private final ExpenseRepository expenseRepository;

	@Override
	public byte[] generateMonthlyReport(int month, int year, String email) {

		List<Expense> expenses = expenseRepository.findByMonthAndYear(month, year, email);

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		Document document = new Document();

		try {
			PdfWriter.getInstance(document, out);
			document.open();

			document.add(new Paragraph("Monthly Expense Report"));
			document.add(new Paragraph("User: " + email));
			document.add(new Paragraph(" "));

			for (Expense e : expenses) {
				document.add(new Paragraph(e.getExpenseDate() + " | " + e.getCategory() + " | ₹" + e.getAmount()));
			}

			document.close();

		} catch (Exception ex) {
			throw new RuntimeException("Error generating PDF", ex);
		}

		return out.toByteArray();
	}

}
