package com.zidioProject.eems.Services;

import java.util.List;

import com.zidioProject.eems.Entity.User;

public interface UserService {
	
	// Create a new user
    public User createUser(User user);
    
    // Find a user by ID
    public User findByUid(Long uid);
    
    // Update user details
    public User updateUser(Long uid, User updatedUser);
    
    // Delete a user
    public void deleteUser(Long uid);
    
    // Get all users
    public List<User> showUsers();
	
}
