package com.zidioProject.eems.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zidioProject.eems.Entity.Employee;
import com.zidioProject.eems.Entity.Expenses;
import com.zidioProject.eems.Entity.Status;
import com.zidioProject.eems.ServicesImplementation.EmployeeServiceImpl;
import com.zidioProject.eems.ServicesImplementation.ExpenseServiceImpl;

import jakarta.validation.Valid;;

@RestController
@RequestMapping("/api/dashboard")
public class GeneralController {

	@Autowired
	private ExpenseServiceImpl expenseService;

	@Autowired
	private EmployeeServiceImpl empService;

	@GetMapping("/profile")
	public Employee displayProfile() {
		return empService.displayProfile();
	}

	@PutMapping("/profile/update")
	public ResponseEntity<String> updateName(@RequestBody Employee emp) {
		return ResponseEntity.ok(empService.updateProfileInfo(emp));
	}

	@PutMapping("/profile/update-email")
	public ResponseEntity<String> updateEmail(@RequestBody Employee newEmail) {
		return ResponseEntity.ok(empService.updateEmail(newEmail));
	}

	@PutMapping("/profile/update-password")
	public ResponseEntity<String> updatePassword(@RequestBody Employee emp) {
		return ResponseEntity.ok(empService.updatePassword(emp));
	}

	@PreAuthorize("hasRole('Admin') or hasRole('Manager')")
	@PutMapping("/update-status/{id}")
	public ResponseEntity<String> updateExpenseStatus(@PathVariable Integer id, @RequestParam Status status) {
		String result = expenseService.updateStatus(id, status);
		return ResponseEntity.ok(result);
	}

	@GetMapping("view-invoice/{filename}")
	public ResponseEntity<Resource> viewInvoice(@PathVariable String filename) {
		try {
			Path filePath = Paths.get("invoices").resolve(filename).normalize();
			Resource resource = new UrlResource(filePath.toUri());

			if (!resource.exists() || !resource.isReadable()) {
				return ResponseEntity.notFound().build();
			}

			// Detect content type based on file extension
			String contentType;
			String lowerName = filename.toLowerCase();

			if (lowerName.endsWith(".pdf")) {
				contentType = MediaType.APPLICATION_PDF_VALUE;
			} else if (lowerName.endsWith(".jpg") || lowerName.endsWith(".jpeg")) {
				contentType = MediaType.IMAGE_JPEG_VALUE;
			} else if (lowerName.endsWith(".png")) {
				contentType = MediaType.IMAGE_PNG_VALUE;
			} else {
				// Default fallback
				contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
			}

			return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
					.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"").body(resource);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PreAuthorize("hasRole('Admin') or hasRole('Manager')")
	@GetMapping("/expense/processed-requests")
	public ResponseEntity<List<Expenses>> findProccessedExpenseRequests(){
		List<Expenses> newList = expenseService.findApprovedOrRejectedExpenses();
		return ResponseEntity.ok(newList);
	}
	
	

}
