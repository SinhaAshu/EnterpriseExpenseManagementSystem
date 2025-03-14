package com.zidioProject.eems.ServicesImplementation;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.zidioProject.eems.Entity.Expense;
import com.zidioProject.eems.Entity.User;
import com.zidioProject.eems.Repository.ExpenseRepository;
import com.zidioProject.eems.Services.ExpenseService;

public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;

	@Override
	public Expense addExpense(Expense expense) {
		expense.setStatus("PENDING");
		return expenseRepository.save(expense);
	}

	@Override
	public List<Expense> getEmployeeExpenses(User employee) {
		return expenseRepository.findByEmployee(employee);
	}

	@Override
	public List<Expense> getPendingExpensesForManager() {
		return expenseRepository.findByStatus("PENDING").stream().filter(expense -> expense.getAmount() < 20000)
				.collect(Collectors.toList());
	}

	@Override
	public List<Expense> getPendingExpensesForAdmin() {
		return expenseRepository.findByStatus("PENDING").stream().filter(expense -> expense.getAmount() >= 20000)
				.collect(Collectors.toList());
	}

	@Override
	public Expense updateExpenseStatus(Long id, String status) {
		Expense expense = expenseRepository.findById(id).orElseThrow();
		expense.setStatus(status);
		return expenseRepository.save(expense);
	}

}
