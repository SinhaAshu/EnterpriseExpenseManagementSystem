package com.zidioProject.eems.Services;

import java.util.List;

import com.zidioProject.eems.DTO.ExpenseStatsDTO;

public interface AnalyticsService {
	
	List<ExpenseStatsDTO> getMonthlyTrends(int year);
	
    List<ExpenseStatsDTO> getYearlyTrends();
    
    List<ExpenseStatsDTO> getCategoryBreakdown(int year);
    
    byte[] exportToExcel(int year);
    byte[] exportToPDF(int year);

}
