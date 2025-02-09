package com.zidioProject.eems.ServicesImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zidioProject.eems.Entity.User;
import com.zidioProject.eems.Repository.UserRepository;
import com.zidioProject.eems.Services.UserService;

public class UserServiceImplementation implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByUid(Long uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(Long uid, User updatedUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Long uid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> showUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
