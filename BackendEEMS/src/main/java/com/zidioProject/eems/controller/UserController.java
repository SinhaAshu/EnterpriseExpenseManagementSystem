package com.zidioProject.eems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zidioProject.eems.Entity.Role;
import com.zidioProject.eems.Entity.User;
import com.zidioProject.eems.Services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String showRegistrationPage(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roles", Role.values());
		return "register";
	}

	@PostMapping("/register")
	public String saveUser(@ModelAttribute User user) {
		userService.save(user);
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestParam String email, @RequestParam String password, Model model) {
		User user = userService.loginUser(email, password);
		if (user != null) {
			model.addAttribute("user",user);
			return "home";
		}
		return "redirect:/login?error";
	}
	
}
