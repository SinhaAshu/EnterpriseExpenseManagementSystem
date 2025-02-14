package com.zidioProject.eems.Services;

import com.zidioProject.eems.Entity.User;

public interface UserService {
	
	public void save(User user);
	
	public User loginUser(String email, String password);
	
}

