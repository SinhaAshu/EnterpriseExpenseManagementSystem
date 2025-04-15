package com.zidioProject.eems.Services;

import java.util.List;
import java.util.Map;

import com.zidioProject.eems.Entity.Employee;
import com.zidioProject.eems.Entity.Roles;

public interface EmployeeService {

	public Employee addEmployee(Employee emp);
	
	public List<Employee> viewEmployees();
	
	public Employee displayProfile();
	
	public String updateProfileInfo(Employee emp);
	
	public String updateEmail(Employee emp);
	
	public String updatePassword(Employee emp);
	
	public String deleteEmployee(String username);
	
	public List<Employee> findByRoles(Roles role);
	
	public Map<String, String> loginVerification(Employee employee);
}
