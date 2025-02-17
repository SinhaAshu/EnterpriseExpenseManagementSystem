package com.zidioProject.eems.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {


	
	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	private int eid;
	
	@Column(nullable = false)  
	private float amount;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private LocalDate date;
	
}
