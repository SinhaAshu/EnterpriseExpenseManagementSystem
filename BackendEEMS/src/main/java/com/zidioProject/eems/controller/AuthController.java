package com.zidioProject.eems.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.zidioProject.eems.Entity.Employee;
import com.zidioProject.eems.ServicesImplementation.EmployeeServiceImpl;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	private EmployeeServiceImpl employeeService;

	// registering an employee
	@PostMapping("/register")
	public Employee registerEmp(@Valid @RequestBody Employee employee) {
		try {
			return employeeService.addEmployee(employee);
		} catch (Exception e) {
			throw new ResponseStatusException(
		            HttpStatus.BAD_REQUEST, "Failed to register employee: " + e.getMessage(), e);
		}
	}

	@PostMapping("/auth/login")
	public Map<String, String> loginVerification(@RequestBody Employee employee) {
		return employeeService.loginVerification(employee);
	}
}
