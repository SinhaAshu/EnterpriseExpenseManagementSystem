package com.zidioProject.eems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zidioProject.eems.Entity.HandleLogin;
import com.zidioProject.eems.Entity.Role;
import com.zidioProject.eems.Entity.User;
import com.zidioProject.eems.ServicesImplementation.UserServiceImplementation;


@RestController
@CrossOrigin(originPatterns = "http://localhost:5173/")
public class UserController {

	@Autowired
	private UserServiceImplementation userServiceImplementation;
	
	@GetMapping("/viewAllUsers")
	public List<User> findListofUsers(){
		return userServiceImplementation.viewUsers();
	}

	@PostMapping("/registration")
    public User registerUser(@RequestBody User user) {
    	return userServiceImplementation.registerUser(user);
    }
	
	@GetMapping("/role/{role}")
	public List<User> findUsersByRole(@PathVariable Role role){
		return userServiceImplementation.viewUserByRole(role);
	}
		
	@PostMapping("/login")
	public String loginUser(@RequestBody HandleLogin login) {
		return userServiceImplementation.loginUser(login);
	}
	
}
