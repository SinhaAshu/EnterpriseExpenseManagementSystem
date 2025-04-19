package com.zidioProject.eems.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Expenses {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "please fill the category!")
	@Enumerated(EnumType.STRING)
	private Category category;
	
	private String invoice;
	
	@NotBlank(message = "please fill the description!")
	private String description;
	
	@Min(value = 0, message = "Amount must be positive")
	private float amount;
	
	private Date date;
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.PENDING;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "approved_by")
	private Employee approvedBy;

}
