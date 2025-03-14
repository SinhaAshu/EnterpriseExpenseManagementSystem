package com.zidioProject.eems.ServicesImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zidioProject.eems.Entity.HandleLogin;
import com.zidioProject.eems.Entity.Role;
import com.zidioProject.eems.Entity.User;
import com.zidioProject.eems.Repository.UserRepository;
import com.zidioProject.eems.Services.UserService;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User registerUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> viewUsers() {
		return userRepository.findAll();
	}

	@Override
	public List<User> viewUserByRole(Role role) {
		return userRepository.findByRole(role);
	}

//	@Override
//	public String loginUser(HandleLogin login) {
//      User user = userRepository.findByEmail(login.getUsername());
//      if( user == null ) {
//    	  return "Username doesn't exist!";
//      }
//      else {
//    	  Role role = user.getRole();
//		if(user.getPassword().equals(login.getPassword()) && role.equals(login.getRole()) ) {
//			return "Login successful!";
//		}
//	}
//		return "Login unsuccessful!";
//	}

	@Override
	public User loginUser(String email, String password) {
		User user = userRepository.findByEmail(email);
		if(user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

}
