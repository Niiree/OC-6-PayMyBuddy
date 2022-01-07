package com.paymybuddy.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.paymybuddy.app.repository.UserRepository;

@Controller
public class AppController {
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/essai")
	public String viewHomePage() {
		return "essai";
	}
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
}
