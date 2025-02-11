package com.zidioProject.eems.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zidioProject.eems.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
	
}