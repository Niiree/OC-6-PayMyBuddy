package com.paymybuddy.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.paymybuddy.app.repository.UserRepository;


@Controller
public class AppController {


	@GetMapping("/")
	public String home() {
		return "home";
	}

	
}
