package com.zidioProject.eems.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.zidioProject.eems.Entity.Expenses;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class PDFExporter {
	
	 public static byte[] exportExpensesToPDF(List<Expenses> expensesList) {
	        Document document = new Document();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();

	        try {
	            PdfWriter.getInstance(document, out);
	            document.open();

	            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
	            Paragraph title = new Paragraph("Expense Report", headerFont);
	            title.setAlignment(Element.ALIGN_CENTER);
	            title.setSpacingAfter(20);
	            document.add(title);

	            PdfPTable table = new PdfPTable(8);
	            table.setWidthPercentage(100);
	            table.setWidths(new int[]{1, 2, 2, 3, 4, 2, 3, 2});

	            // Headers
	            String[] headers = {"ID", "Category", "Amount", "Date", "Description", "Status", "Approved By", "Emp ID"};
	            for (String header : headers) {
	                PdfPCell cell = new PdfPCell(new Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                table.addCell(cell);
	            }

	            // Data Rows
	            for (Expenses exp : expensesList) {
	                table.addCell(String.valueOf(exp.getId()));
	                table.addCell(exp.getCategory().toString());
	                table.addCell(String.valueOf(exp.getAmount()));
	                table.addCell(exp.getDate().toString());
	                table.addCell(exp.getDescription());
	                table.addCell(exp.getStatus().toString());
	                table.addCell(exp.getApprovedBy() != null ? exp.getApprovedBy().getFull_name() : "N/A");
	                table.addCell(String.valueOf(exp.getEmployee() != null ? exp.getEmployee().getUid() : 0));
	            }

	            document.add(table);
	            document.close();

	            return out.toByteArray();

	        } catch (DocumentException e) {
	            throw new RuntimeException("Error generating PDF: " + e.getMessage());
	        }
	    }

}
