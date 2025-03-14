package com.zidioProject.eems.Services;

import java.util.List;

import com.zidioProject.eems.Entity.Expense;
import com.zidioProject.eems.Entity.User;

public interface ExpenseService {
	
	public Expense addExpense(Expense expense);
	
	public List<Expense> getEmployeeExpenses(User employee);
	
	public List<Expense> getPendingExpensesForManager();
	
	public List<Expense> getPendingExpensesForAdmin();
	
	public Expense updateExpenseStatus(Long id, String status);
	
}
