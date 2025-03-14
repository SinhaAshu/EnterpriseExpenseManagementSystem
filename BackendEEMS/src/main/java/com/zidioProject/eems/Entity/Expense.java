package com.zidioProject.eems.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="expenses")
public class Expense {

	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	private Long id;
	
	@Column(nullable = false)  
	private Double amount;
	
	@Column(nullable = false)
	private String description;
	
	private String status;
	
	@Column(nullable = false)
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private User employee;
	
}
