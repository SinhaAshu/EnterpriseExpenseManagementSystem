package com.zidioProject.eems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.zidioProject.eems.Entity.User;

@Controller
public class DashboardController {
	
	@GetMapping("/dashboard")
	public String showDashboard(@SessionAttribute("user") User user) {
		if (user.getRole().equals("MANAGER")) {
			return "manager_dashboard";
		} else if (user.getRole().equals("ADMIN")) {
			return "admin_dashboard";
		}
		return "dashboard";
	}
	
}
