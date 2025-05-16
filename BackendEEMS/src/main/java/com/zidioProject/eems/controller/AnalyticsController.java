package com.zidioProject.eems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zidioProject.eems.DTO.ExpenseStatsDTO;
import com.zidioProject.eems.Services.AnalyticsService;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

	@Autowired
    private AnalyticsService analyticsService;

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/monthly")
    public ResponseEntity<List<ExpenseStatsDTO>> getMonthlyTrends(@RequestParam int year) {
        return ResponseEntity.ok(analyticsService.getMonthlyTrends(year));
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/yearly")
    public ResponseEntity<List<ExpenseStatsDTO>> getYearlyTrends() {
        return ResponseEntity.ok(analyticsService.getYearlyTrends());
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/category")
    public ResponseEntity<List<ExpenseStatsDTO>> getCategoryBreakdown(@RequestParam int year) {
        return ResponseEntity.ok(analyticsService.getCategoryBreakdown(year));
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/export/excel")
    public ResponseEntity<byte[]> exportExcel(@RequestParam int year) {
        byte[] data = analyticsService.exportToExcel(year);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=expense_report.xlsx")
                .body(data);
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/export/pdf")
    public ResponseEntity<byte[]> exportPdf(@RequestParam int year) {
        byte[] data = analyticsService.exportToPDF(year);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=expense_report.pdf")
                .body(data);
    }
}
