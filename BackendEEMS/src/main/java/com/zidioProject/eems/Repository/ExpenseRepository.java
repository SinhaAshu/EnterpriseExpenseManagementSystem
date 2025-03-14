package com.zidioProject.eems.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zidioProject.eems.Entity.Expense;
import com.zidioProject.eems.Entity.User;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	List<Expense> findByEmployee(User employee);

	List<Expense> findByStatus(String status);
}
