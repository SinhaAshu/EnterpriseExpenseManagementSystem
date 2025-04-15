package com.zidioProject.eems.Entity;

import java.io.Serial;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;
	
	private Employee employee;
	
	Roles role;

	public CustomUserDetails(Employee employee) {
		this.employee = employee;
		this.role = employee.getRole();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(role.getAuthority()));
	}

	@Override
	public String getPassword() {
		return employee.getPassword();
	}

	@Override
	public String getUsername() {
		return employee.getEmail();
	}

	public Roles getRole() {
		return this.role;
	}
	

}
