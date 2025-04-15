package com.zidioProject.eems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zidioProject.eems.Entity.Employee;
import com.zidioProject.eems.Entity.Status;
import com.zidioProject.eems.ServicesImplementation.EmployeeServiceImpl;
import com.zidioProject.eems.ServicesImplementation.ExpenseServiceImpl;;



@RestController
@CrossOrigin(originPatterns = "http://localhost:5173/")
@RequestMapping("/api/dashboard")
public class GeneralController {
	
	@Autowired
	private ExpenseServiceImpl expenseService;

	@Autowired
	private EmployeeServiceImpl empService;
	
	@GetMapping("/profile")
	public Employee displayProfile() {
		return empService.displayProfile();
	}
	
	@PutMapping("/profile/update")
	public ResponseEntity<String> updateName(@RequestBody Employee emp) {
	    return ResponseEntity.ok(empService.updateProfileInfo(emp));
	}

	@PutMapping("/profile/update-email")
	public ResponseEntity<String> updateEmail(@RequestBody Employee emp) {
	    return ResponseEntity.ok(empService.updateEmail(emp));
	}

	@PutMapping("/profile/update-password")
	public ResponseEntity<String> updatePassword(@RequestBody Employee emp) {
	    return ResponseEntity.ok(empService.updatePassword(emp));
	}
	
	@PreAuthorize("hasAnyRole('Admin', 'Manager')")
	@PutMapping("/update-status/{id}")
	public ResponseEntity<String> updateExpenseStatus(
	        @PathVariable Integer id,
	        @RequestParam Status status) {

	    String result = expenseService.updateStatus(id, status);
	    return ResponseEntity.ok(result);
	}
}
