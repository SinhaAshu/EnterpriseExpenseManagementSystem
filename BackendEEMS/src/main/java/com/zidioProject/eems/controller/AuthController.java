package com.zidioProject.eems.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zidioProject.eems.Entity.Employee;
import com.zidioProject.eems.ServicesImplementation.EmployeeServiceImpl;


@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	private EmployeeServiceImpl employeeService;

	// registering an employee
	@PostMapping("/register")
	public Employee registerEmp(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}

	@PostMapping("/auth/login")
	public Map<String, String> loginVerification(@RequestBody Employee employee) {
		return employeeService.loginVerification(employee);
	}
}
