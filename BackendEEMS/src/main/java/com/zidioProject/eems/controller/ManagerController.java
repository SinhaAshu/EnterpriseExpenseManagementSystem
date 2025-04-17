package com.zidioProject.eems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zidioProject.eems.Entity.Expenses;
import com.zidioProject.eems.ServicesImplementation.ExpenseServiceImpl;


@RestController
@RequestMapping("/api/manager")
public class ManagerController {
	
	@Autowired
	private ExpenseServiceImpl expenseService;
	
	@PreAuthorize("hasRole('Manager')")
	@GetMapping("/expense/requests")
	public List<Expenses> viewPendingRequestExpensesForManager(){
		return expenseService.findExpensesForManager();
	}
	
	

}
