package com.zidioProject.eems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zidioProject.eems.Entity.Category;
import com.zidioProject.eems.Entity.Expenses;
import com.zidioProject.eems.ServicesImplementation.ExpenseServiceImpl;

import jakarta.validation.Valid;

@RequestMapping("/api/employee")
@RestController
public class EmpController {
	
	@Autowired
	private ExpenseServiceImpl expenseService;
	
	//adding new expense
	@PreAuthorize("hasRole('Employee')")
	@PostMapping("/addExpense")
	public ResponseEntity<String> addingExpense(@Valid
			@RequestParam Category category, 
			@RequestParam(value = "invoice", required = false) MultipartFile file, 
			@RequestParam String description, 
			@RequestParam float amount) {
		String message = expenseService.addExpense(category, file, description, amount);
		return ResponseEntity.ok(message);
	}
	
	//updating existing expense
	@PreAuthorize("hasRole('Employee')")
	@PutMapping("/update-expense/{id}")
	public ResponseEntity<String> updateExpense(@Valid
	        @PathVariable Integer id,
	        @RequestParam String description,
	        @RequestParam float amount,
	        @RequestParam Category category,
	        @RequestParam(value = "invoice", required = false) MultipartFile file) {
	    
	    String updated = expenseService.updateExpense(id, description, amount, category, file);
	    return ResponseEntity.ok(updated);
	}
	
	//deleting an expense
	@DeleteMapping("/delete-expense/{id}")
	public ResponseEntity<String> deleteExpense(@PathVariable Integer id){
		String message = expenseService.deleteExpense(id);
		return ResponseEntity.ok(message);
	}
	
	//viewing the expense list of individual employee
	@GetMapping("/view-expense")
	public ResponseEntity<List<Expenses>> viewExpenseListOfEmployee(){
		return ResponseEntity.ok(expenseService.findExpensesByEmployee());
	}
	
	
	@GetMapping("/test")
	public String test() {
		return "hello";
	}
	
	
}
