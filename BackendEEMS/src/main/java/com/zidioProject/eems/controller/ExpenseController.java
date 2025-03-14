package com.zidioProject.eems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.zidioProject.eems.Entity.Expense;
import com.zidioProject.eems.Entity.User;
import com.zidioProject.eems.Services.ExpenseService;

@Controller
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@GetMapping("/addExpense")
	public String showAddExpensePage() {
		return "add_expense";
	}

	@PostMapping("/addExpense")
	public String addExpense(@ModelAttribute Expense expense, @SessionAttribute("user") User user) {
		expense.setEmployee(user);
		expenseService.addExpense(expense);
		return "redirect:/dashboard";
	}

	@GetMapping("/expenses")
	public String viewExpenses(@SessionAttribute(name = "user", required = false) User user, Model model) {
		if (user == null) {
			return "redirect:/login";
		}
		model.addAttribute("expenses", expenseService.getEmployeeExpenses(user));
		return "expenses";
	}

}
