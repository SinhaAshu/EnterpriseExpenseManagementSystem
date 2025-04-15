package com.zidioProject.eems.ServicesImplementation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zidioProject.eems.Entity.Category;
import com.zidioProject.eems.Entity.Employee;
import com.zidioProject.eems.Entity.Expenses;
import com.zidioProject.eems.Entity.Roles;
import com.zidioProject.eems.Entity.Status;
import com.zidioProject.eems.Repository.EmployeeRepo;
import com.zidioProject.eems.Repository.ExpenseRepository;
import com.zidioProject.eems.Services.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Autowired
	private EmployeeRepo employeeRepo;

	// adding new expense
		public String addExpense(Category category, MultipartFile file, String description, float amount) {
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			Employee existingEmployee = employeeRepo.findByEmail(email);

			if (existingEmployee == null) {
				throw new RuntimeException("Empployee not found!");
			} else {
				Expenses newExpense = new Expenses();
				newExpense.setCategory(category);
				
	            String fileDesination = "invoices/";
	            File fileDirectory = new File(fileDesination);
	            if(!fileDirectory.exists()) {
	            	fileDirectory.mkdirs();
	            }
				try {
					Path filePath = Paths.get(fileDesination + file.getOriginalFilename());
					Files.write(filePath, file.getBytes());
					newExpense.setInvoicePath(filePath.toString());
				} catch (IOException e) {
					throw new RuntimeException("Error processing file", e);
				}

				newExpense.setDescription(description);
				newExpense.setAmount(amount);
				newExpense.setStatus(Status.PENDING);
				newExpense.setDate(new Date());
				newExpense.setEmployee(existingEmployee);

			    expenseRepository.save(newExpense);
			    return "Expense added!";
			}
		}

		// updating the expense
		public String updateExpense(Integer expenseId, String description, float amount, Category category, MultipartFile file) {
		    String email = SecurityContextHolder.getContext().getAuthentication().getName();

		    Expenses expense = expenseRepository.findById(expenseId)
		            .orElseThrow(() -> new RuntimeException("Expense not found!"));

		    if (!expense.getEmployee().getEmail().equals(email)) {
		        throw new RuntimeException("You are not authorized to update this expense.");
		    }

		    if (expense.getStatus() != Status.PENDING) {
		        throw new RuntimeException("Only pending expenses can be updated.");
		    }

		    expense.setDescription(description);
		    expense.setAmount(amount);
		    expense.setCategory(category);

		    if (file != null && !file.isEmpty()) {
		        // Delete the old invoice file if it exists
		        String oldPath = expense.getInvoicePath();
		        if (oldPath != null) {
		            File oldFile = new File(oldPath);
		            if (oldFile.exists()) {
		                oldFile.delete();
		            }
		        }

		        // Save the new file
		        String fileDirectoryPath = "invoices/";
		        File fileDirectory = new File(fileDirectoryPath);
		        if (!fileDirectory.exists()) {
		            fileDirectory.mkdirs(); // create directory if not exists
		        }

		        try {
		            Path newFilePath = Paths.get(fileDirectoryPath + file.getOriginalFilename());
		            Files.write(newFilePath, file.getBytes());
		            expense.setInvoicePath(newFilePath.toString());
		        } catch (IOException e) {
		            throw new RuntimeException("Error saving invoice file", e);
		        }
		    }

		    expenseRepository.save(expense);
		    return "Updated successfully!";
		}


		// deleting the expense if status is pending and employee is authenticated
		public String deleteExpense(Integer expenseId) {
			String email = SecurityContextHolder.getContext().getAuthentication().getName();

			Expenses expense = expenseRepository.findById(expenseId)
					.orElseThrow(() -> new RuntimeException("Expense not found!"));

			Employee user = employeeRepo.findByEmail(email);

			// verifying if the employee is an admin or not
			if (user.getRole().equals(Roles.Admin)) {
				expenseRepository.deleteById(expenseId);
				return "Expense deleted successfully!";
			}

			/* checking if the normal employee is authenticated
			   checking if the status is pending or not */
			if (!expense.getEmployee().getEmail().equals(email)) {
				throw new RuntimeException("You are not authorized to delete this expense.");
			}

			else if (expense.getStatus() != Status.PENDING) {
				throw new RuntimeException("Only pending expenses can be deleted.");
			} else {
				expenseRepository.delete(expense);
				return "Expense deleted successfully!";
			}
		}
		
		//view expense list for the individual employee
			public List<Expenses> findExpensesByEmployee() {
			    String email = SecurityContextHolder.getContext().getAuthentication().getName();
			    
			    Employee existingEmployee = employeeRepo.findByEmail(email);
			    if (existingEmployee == null) {
			        throw new RuntimeException("Employee not found");
			    }

			    return expenseRepository.findByEmployee_Uid(existingEmployee.getUid());
			}
			
	      //view pending expenses for manager
			public List<Expenses> findExpensesForManager() {
				List<Expenses> expenses = expenseRepository.findByStatusAndAmountIsLessThanEqual(Status.PENDING, 30000f);
				return expenses;
			}
			
			//view pending expenses for admin
			public List<Expenses> findExpensesForAdmin() {
				List<Expenses> expenses = expenseRepository.findByStatusAndAmountGreaterThan(Status.PENDING, 30000f);
				return expenses;
			}
			
			//approve or reject status
			public String updateStatus(Integer id, Status newStatus) {
			    Expenses existingExpense = expenseRepository.findById(id)
			            .orElseThrow(() -> new RuntimeException("No such expense exists!"));

			    if (existingExpense.getStatus() != Status.PENDING) {
			        return "This expense has already been processed and cannot be updated again.";
			    }

			    if (newStatus == Status.PENDING) {
			        return "Invalid action. Status can only be updated to APPROVED or REJECTED.";
			    }

			    // ✅ Capture the authenticated approver's email
			    String approverEmail = SecurityContextHolder.getContext().getAuthentication().getName();

			    existingExpense.setStatus(newStatus);
			    existingExpense.setApprovedBy(approverEmail);
			    expenseRepository.save(existingExpense);

			    return newStatus.name();
			}

			
		public List<Expenses> getAllExpenses() {
			return expenseRepository.findAll();
		}
}
