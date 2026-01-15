package com.smart_expense_tracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smart_expense_tracker.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	List<Expense> findByUserEmail(String userEmail);

	@Query("SELECT SUM(e.amount) FROM Expense e WHERE MONTH(e.expenseDate)= :month AND YEAR(e.expenseDate)= :year AND e.userEmail= :email")
	Double getMonthlyTotal(@Param("month") int month, @Param("year") int year, @Param("email") String email);

	@Query("SELECT e.category, SUM(e.amount) FROM Expense e "
			+ "WHERE MONTH(e.expenseDate) = :month AND YEAR(e.expenseDate) = :year AND e.userEmail = :email "
			+ "GROUP BY e.category")
	List<Object[]> getCategorySummary(@Param("month") int month, @Param("year") int year, @Param("email") String email);

	@Query("""
			SELECT MONTH(e.expenseDate), SUM(e.amount)
			FROM Expense e
			WHERE e.userEmail = :email
			GROUP BY MONTH(e.expenseDate)
			ORDER BY MONTH(e.expenseDate)
			""")
	List<Object[]> getMonthlyTrend(String email);

	@Query("""
			SELECT e.category, SUM(e.amount)
			FROM Expense e
			WHERE e.userEmail = :email
			GROUP BY e.category
			ORDER BY SUM(e.amount) DESC
			""")
	List<Object[]> getTopCategories(String email);

	@Query("""
			SELECT DAY(e.expenseDate), SUM(e.amount)
			FROM Expense e
			WHERE MONTH(e.expenseDate)=:month AND YEAR(e.expenseDate)=:year AND e.userEmail=:email
			GROUP BY DAY(e.expenseDate)
			ORDER BY DAY(e.expenseDate)
			""")
	List<Object[]> getDailyTrend(int month, int year, String email);
	
	@Query("""
		    SELECT e
		    FROM Expense e
		    WHERE MONTH(e.expenseDate) = :month
		      AND YEAR(e.expenseDate) = :year
		      AND e.userEmail = :email
		""")
		List<Expense> findByMonthAndYear(@Param("month") int month,
		                                 @Param("year") int year,
		                                 @Param("email") String email);


}
