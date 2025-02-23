package com.zidioProject.eems.Services;

import java.util.List;
import com.zidioProject.eems.Entity.Role;
import com.zidioProject.eems.Entity.User;

public interface UserService {
	
	public User registerUser(User user);
	
	public List<User> viewUsers();
	
	public List<User> viewUserByRole(Role role);
	
	public User loginUser(String email, String password);
	
}

