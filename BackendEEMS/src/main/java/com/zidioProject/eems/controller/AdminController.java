package com.zidioProject.eems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.zidioProject.eems.Entity.Role;
import com.zidioProject.eems.Entity.User;
import com.zidioProject.eems.Services.ExpenseService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ExpenseService expenseService;

	@GetMapping("/dashboard")
	public String adminDashboard(@SessionAttribute("user") User user, Model model) {
		if (user.getRole() != Role.ADMIN) {
			return "redirect:/dashboard";
		}
		model.addAttribute("expenses", expenseService.getPendingExpensesForAdmin());
		return "admin_dashboard";
	}

	@PostMapping("/approve")
	public String approveExpense(@RequestParam Long id) {
		expenseService.updateExpenseStatus(id, "APPROVED");
		return "redirect:/admin/dashboard";
	}

	@PostMapping("/reject")
	public String rejectExpense(@RequestParam Long id) {
		expenseService.updateExpenseStatus(id, "REJECTED");
		return "redirect:/admin/dashboard";
	}
}
