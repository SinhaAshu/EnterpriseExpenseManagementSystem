package com.zidioProject.eems.Services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.zidioProject.eems.Entity.Category;
import com.zidioProject.eems.Entity.Expenses;
import com.zidioProject.eems.Entity.Status;


public interface ExpenseService {
	
	public String addExpense(Category category, MultipartFile file, String description, float amount);
	
	public String updateExpense(Integer expenseId, String description, float amount, Category category, MultipartFile file);
	
	public String deleteExpense(Integer expenseId);
	
	public List<Expenses> findExpensesByEmployee();
	
	public List<Expenses> findExpensesForManager();
	
	public List<Expenses> findExpensesForAdmin();
	
	public String updateStatus(Integer id, Status newStatus);
	
	public List<Expenses> getAllExpenses();
	
	public List<Expenses> findApprovedOrRejectedExpenses();
	
}
