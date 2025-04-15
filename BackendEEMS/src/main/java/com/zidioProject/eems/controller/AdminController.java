package com.zidioProject.eems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zidioProject.eems.Entity.Employee;
import com.zidioProject.eems.Entity.Expenses;
import com.zidioProject.eems.Entity.Roles;
import com.zidioProject.eems.ServicesImplementation.EmployeeServiceImpl;
import com.zidioProject.eems.ServicesImplementation.ExpenseServiceImpl;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(originPatterns = "http://localhost:5173/")
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private ExpenseServiceImpl expenseService;
	
	@Autowired
	private EmployeeServiceImpl employeeService;
	
	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/expense/requests")
	public List<Expenses> viewPendingRequestsExpensesForAdmin(){
		return expenseService.findExpensesForAdmin();
	}
	
	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/view-employee")
	public List<Employee> viewEmployeeByRole(@RequestParam Roles role) {
		return employeeService.findByRoles(role);
	}
	
	@PreAuthorize("hasRole('Admin')")
	@DeleteMapping("/delete-employee")
	public ResponseEntity<String> deleteEmployee(@RequestParam String email) {
	    String result = employeeService.deleteEmployee(email);
	    return ResponseEntity.ok(result);
	}
}
