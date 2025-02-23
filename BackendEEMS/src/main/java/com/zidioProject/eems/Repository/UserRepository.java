package com.zidioProject.eems.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zidioProject.eems.Entity.Role;
import com.zidioProject.eems.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
	User findByEmail(String email);
	List<User> findByRole(Role role);
	
}

