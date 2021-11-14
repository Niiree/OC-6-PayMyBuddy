package com.paymybuddy.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.paymybuddy.app.models.AccountBank;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repository.UserRepository;
import com.paymybuddy.app.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/userCreation")
    public String userCreatForm(Model model) {
        model.addAttribute("createUserForm", new User());
        return "createUserForm";
    }

    @PostMapping("/userCreation")
    public String submissionResult(@ModelAttribute("userCreatForm") User user) {
    	userService.saveUser(user);
        return "index";
    }
	
	
}
