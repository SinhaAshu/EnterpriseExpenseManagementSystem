package com.zidioProject.eems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zidioProject.eems.Entity.Role;
import com.zidioProject.eems.Entity.User;
import com.zidioProject.eems.Services.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(originPatterns = "http://localhost:5173/")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/viewAllUsers")
	public List<User> findListofUsers() {
		return userService.viewUsers();
	}

	@PostMapping("/registration")
	public User registerUser(@RequestBody User user) {
		return userService.registerUser(user);
	}

	@GetMapping("/role/{role}")
	public List<User> findUsersByRole(@PathVariable Role role) {
		return userService.viewUserByRole(role);
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	@GetMapping("/register")
	public String showRegisterPage() {
		return "register";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute User user) {
		userService.registerUser(user);
		return "redirect:/login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
		User user = userService.loginUser(email, password);
		if (user != null) {
			session.setAttribute("user", user);

			if (user.getRole() == Role.ADMIN) {
				return "redirect:/admin/dashboard";
			} else if (user.getRole() == Role.MANAGER) {
				return "redirect:/manager/dashboard";
			} else {
				return "redirect:/dashboard";
			}
		}
		return "redirect:/login?error";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
