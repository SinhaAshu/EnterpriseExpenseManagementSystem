package com.zidioProject.eems.ServicesImplementation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.zidioProject.eems.Entity.CustomUserDetails;
import com.zidioProject.eems.Entity.Employee;
import com.zidioProject.eems.Entity.Roles;
import com.zidioProject.eems.Repository.EmployeeRepo;
import com.zidioProject.eems.Services.EmployeeService;
import com.zidioProject.eems.security.JwtService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private JwtService jwtService;

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	//registering employee
	public Employee addEmployee(Employee emp) {
		emp.setPassword(passwordEncoder.encode(emp.getPassword()));
		return employeeRepo.save(emp);
	}

	//viewing all employee
	public List<Employee> viewEmployees() {
		return employeeRepo.findAll();
	}
	
	//display profile
	public Employee displayProfile() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		Employee employee = employeeRepo.findByEmail(email);
		if (employee == null) {
	        throw new UsernameNotFoundException("Employee not found for email: " + email);
	    }
		return employee;		
	}
	
	//updating employee
	public String updateProfileInfo(Employee emp) {
	    String email = SecurityContextHolder.getContext().getAuthentication().getName();
	    Employee existingEmployee = employeeRepo.findByEmail(email);

	    if (existingEmployee != null) {
	        existingEmployee.setFull_name(emp.getFull_name());
	        employeeRepo.save(existingEmployee);
	        return "Full name updated successfully!";
	    } else {
	        throw new RuntimeException("Employee not found.");
	    }
	}
	
	//updating email
	public String updateEmail(Employee emp) {
	    String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
	    Employee existingEmployee = employeeRepo.findByEmail(currentEmail);

	    if (existingEmployee != null) {
	        if (existingEmployee.getEmail().equals(emp.getEmail()) ||
	            employeeRepo.existsByEmail(emp.getEmail())) {
	            throw new RuntimeException("This email already exists!");
	        }else {
	        existingEmployee.setEmail(emp.getEmail());
	        employeeRepo.save(existingEmployee);
	        return "Email updated successfully. Please login again.";
	        }
	    } else {
	        throw new RuntimeException("Employee not found.");
	    }
	}
	
	//updating password
	public String updatePassword(Employee emp) {
	    String email = SecurityContextHolder.getContext().getAuthentication().getName();
	    Employee existingEmployee = employeeRepo.findByEmail(email);

	    if (existingEmployee != null) {
	        existingEmployee.setPassword(passwordEncoder.encode(emp.getPassword()));
	        employeeRepo.save(existingEmployee);
	        return "Password updated successfully. Please login again.";
	    } else {
	        throw new RuntimeException("Employee not found.");
	    }
	}
	
	//deleting an employee
	public String deleteEmployee(String username) {
	    Employee emp = employeeRepo.findByEmail(username);
	    if (emp == null) {
	        throw new RuntimeException("Employee with email " + username + " not found.");
	    }
	    employeeRepo.delete(emp);
	    return "Employee deleted successfully.";
	}

	//find employee excluding admin
	public List<Employee> findEmployee(){
		return employeeRepo.findByRoleNot(Roles.Admin);
	}
	
    //finding employee via roles
	public List<Employee> findByRoles(Roles role) {
		return employeeRepo.findByRole(role);
	}

	//method for login
	public Map<String, String> loginVerification(Employee employee) {		
		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(employee.getEmail(),employee.getPassword()));
		if(authentication.isAuthenticated()) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
	
	        Roles actualRole = userDetails.getRole(); 
	  
	        Roles requestedRole = employee.getRole();   

	        if (!actualRole.equals(requestedRole)) {
	        	return Map.of("error", "Login failed - Role mismatch!");
	        }

	        String jwtToken = jwtService.generateToken(employee);

	        return Map.of(
	            "message", "Login successful!",
	            "token", jwtToken
	        );
	    }
	    return Map.of("error", "Login failed - Invalid credentials");
	}
	

}
