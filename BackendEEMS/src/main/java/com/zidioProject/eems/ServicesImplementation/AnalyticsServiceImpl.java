package com.zidioProject.eems.ServicesImplementation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zidioProject.eems.DTO.ExpenseStatsDTO;
import com.zidioProject.eems.Entity.Expenses;
import com.zidioProject.eems.Repository.ExpenseRepository;
import com.zidioProject.eems.Services.AnalyticsService;
import com.zidioProject.eems.utils.ExcelExporter;
import com.zidioProject.eems.utils.PDFExporter;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    @Autowired
    private ExpenseRepository expensesRepository;

    @Override
    public List<ExpenseStatsDTO> getMonthlyTrends(int year) {
        List<Expenses> allExpenses = expensesRepository.findAllByYear(year);

        Map<String, Float> monthlyMap = new TreeMap<>();
        for (Expenses exp : allExpenses) {
            if (exp.getDate() != null) {
                LocalDate localDate = exp.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                String key = localDate.getMonth().toString(); // "JANUARY", etc.
                monthlyMap.put(key, monthlyMap.getOrDefault(key, 0f) + exp.getAmount());
            }
        }
        return monthlyMap.entrySet().stream()
                .map(entry -> new ExpenseStatsDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ExpenseStatsDTO> getYearlyTrends() {
        List<Expenses> allExpenses = expensesRepository.findAll();

        Map<Integer, Float> yearlyMap = new TreeMap<>();
        for (Expenses exp : allExpenses) {
            if (exp.getDate() != null) {
                LocalDate localDate = exp.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int year = localDate.getYear();
                yearlyMap.put(year, yearlyMap.getOrDefault(year, 0f) + exp.getAmount());
            }
        }
        return yearlyMap.entrySet().stream()
                .map(entry -> new ExpenseStatsDTO(String.valueOf(entry.getKey()), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ExpenseStatsDTO> getCategoryBreakdown(int year) {
        List<Expenses> allExpenses = expensesRepository.findAllByYear(year);

        Map<String, Float> categoryMap = new TreeMap<>();
        for (Expenses exp : allExpenses) {
            if (exp.getCategory() != null) {
                String category = exp.getCategory().toString();
                categoryMap.put(category, categoryMap.getOrDefault(category, 0f) + exp.getAmount());
            }
        }
        return categoryMap.entrySet().stream()
                .map(entry -> new ExpenseStatsDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public byte[] exportToExcel(int year) {
        List<Expenses> allExpenses = expensesRepository.findAllByYear(year);
        return ExcelExporter.exportExpensesToExcel(allExpenses);
    }

    @Override
    public byte[] exportToPDF(int year) {
        List<Expenses> allExpenses = expensesRepository.findAllByYear(year);
        return PDFExporter.exportExpensesToPDF(allExpenses);
    }
}
