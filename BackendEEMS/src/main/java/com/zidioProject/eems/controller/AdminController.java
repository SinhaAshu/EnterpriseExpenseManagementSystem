package com.zidioProject.eems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zidioProject.eems.Entity.Employee;
import com.zidioProject.eems.Entity.Expenses;
import com.zidioProject.eems.Entity.Roles;
import com.zidioProject.eems.ServicesImplementation.EmployeeServiceImpl;
import com.zidioProject.eems.ServicesImplementation.ExpenseServiceImpl;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private ExpenseServiceImpl expenseService;
	
	@Autowired
	private EmployeeServiceImpl employeeService;
	
	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/expense/requests")
	public ResponseEntity<List<Expenses>> viewPendingRequestsExpensesForAdmin() {
	    List<Expenses> expenses = expenseService.findExpensesForAdmin();
	    return ResponseEntity.ok(expenses); // 200 OK
	}

	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/view-employee")
	public ResponseEntity<List<Employee>> findEmployee() {
	    List<Employee> employees = employeeService.findEmployee();
	    if (employees.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    }
	    return ResponseEntity.ok(employees);
	}

	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/view-employee/role")
	public ResponseEntity<List<Employee>> viewEmployeeByRole(@RequestParam Roles role) {
	    List<Employee> employees = employeeService.findByRoles(role);
	    return ResponseEntity.ok(employees);
	}
	
	@PreAuthorize("hasRole('Admin')")
	@DeleteMapping("/delete-employee")
	public ResponseEntity<String> deleteEmployee(@RequestParam String email) {
	    try {
	        String result = employeeService.deleteEmployee(email);
	        return ResponseEntity.ok(result); // 200 OK with message
	    } catch (EntityNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with email: " + email);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting employee.");
	    }
	}
	
	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/viewAllExpenses")
	public ResponseEntity<List<Expenses>> displayAllExpenses(){
		List<Expenses> expenses = expenseService.getAllExpenses();
		return ResponseEntity.ok(expenses);
	}
}
