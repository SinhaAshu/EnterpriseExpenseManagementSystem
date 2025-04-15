package com.zidioProject.eems.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zidioProject.eems.Entity.Expenses;
import com.zidioProject.eems.Entity.Status;

@Repository
public interface ExpenseRepository extends JpaRepository<Expenses, Integer> {
	
	List<Expenses> findByStatusAndAmountIsLessThanEqual(Status status, float amount);
	
	List<Expenses> findByStatusAndAmountGreaterThan(Status status, float amount);

	List<Expenses> findByStatus(Status status);

	List<Expenses> findByEmployee_Uid(Long uid);
	
	
}
