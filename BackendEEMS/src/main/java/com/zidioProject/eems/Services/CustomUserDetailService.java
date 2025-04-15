package com.zidioProject.eems.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.zidioProject.eems.Entity.CustomUserDetails;
import com.zidioProject.eems.Entity.Employee;
import com.zidioProject.eems.Repository.EmployeeRepo;

@Component
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Employee existingEmployee = employeeRepo.findByEmail(username);
		 if(existingEmployee == null) {
            throw new UsernameNotFoundException("No such username exists!");
		 }
		 else {
			 return new CustomUserDetails(existingEmployee);
		 }

    }
}
