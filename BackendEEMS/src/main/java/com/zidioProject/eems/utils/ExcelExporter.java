package com.zidioProject.eems.utils;

import com.zidioProject.eems.Entity.Expenses;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {

	public static byte[] exportExpensesToExcel(List<Expenses> expensesList) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Expenses");

            // Header Row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Category", "Amount", "Date", "Description", "Status", "Approved By", "Employee ID"};

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                CellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);
                cell.setCellStyle(style);
            }

            // Data Rows
            int rowIdx = 1;
            for (Expenses exp : expensesList) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(exp.getId());
                row.createCell(1).setCellValue(exp.getCategory().toString());
                row.createCell(2).setCellValue(exp.getAmount());
                row.createCell(3).setCellValue(exp.getDate().toString());
                row.createCell(4).setCellValue(exp.getDescription());
                row.createCell(5).setCellValue(exp.getStatus().toString());
                row.createCell(6).setCellValue(exp.getApprovedBy() != null ? exp.getApprovedBy().getFull_name() : "N/A");
                row.createCell(7).setCellValue(exp.getEmployee() != null ? exp.getEmployee().getUid() : 0);
            }

            workbook.write(out);
            return out.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException("Error generating Excel file: " + e.getMessage());
        }
    }
	
}
