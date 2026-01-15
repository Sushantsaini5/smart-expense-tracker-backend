package com.smart_expense_tracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart_expense_tracker.model.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Long>{
	Optional<Budget> findByUserEmailAndMonthAndYear(String email, int month, int year);

}
