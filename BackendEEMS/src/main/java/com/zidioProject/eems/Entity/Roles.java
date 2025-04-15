package com.zidioProject.eems.Entity;


public enum Roles {
	
	Admin,
	Manager,
	Employee;
	
	public String getAuthority() {
        return "ROLE_" + this.name();
    }
	
}
