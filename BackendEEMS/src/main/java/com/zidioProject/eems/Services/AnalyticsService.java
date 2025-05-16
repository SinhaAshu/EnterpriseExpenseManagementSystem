package com.zidioProject.eems.Services;

import java.util.List;

import com.zidioProject.eems.DTO.ExpenseStatsDTO;

public interface AnalyticsService {
	
	List<ExpenseStatsDTO> getMonthlyTrends();
	
    List<ExpenseStatsDTO> getYearlyTrends();
    
    List<ExpenseStatsDTO> getCategoryBreakdown();
    
    byte[] exportToExcel();
    byte[] exportToPDF();

}
