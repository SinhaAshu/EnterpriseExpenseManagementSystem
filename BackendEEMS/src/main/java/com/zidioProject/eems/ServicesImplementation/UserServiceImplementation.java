package com.zidioProject.eems.ServicesImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zidioProject.eems.Entity.User;
import com.zidioProject.eems.Repository.UserRepository;
import com.zidioProject.eems.Services.UserService;

@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void save(User user) {
		userRepository.save(user);
	}
	
	@Override
	public User loginUser(String email, String password) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			return null;
		}
		if (user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

}
