package com.zidioProject.eems.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    
	@GetMapping("/test")
	public String showMessage() {
		return "Testing controller";
	}
}
