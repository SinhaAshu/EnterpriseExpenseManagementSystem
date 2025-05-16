package com.zidioProject.eems.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseStatsDTO {
	
	private String label;
    private double total;


}
