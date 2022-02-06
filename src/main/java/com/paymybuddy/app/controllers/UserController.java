package com.paymybuddy.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.paymybuddy.app.configuration.Security;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repository.UserRepository;
import com.paymybuddy.app.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private Security security;
	

    @GetMapping("/login")
    public String login() {
    		return "login";
    }
 
  
    @GetMapping("/signup")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }
	
    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);
         
        return "signup_success";
    }
    

    @GetMapping("/contact")
    public String userContact(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);
    
        return "user_contact";
    }
    
    @GetMapping("/profil")
    public String profil(Model model) {
        User user = userService.getUserConnected();
        model.addAttribute("user", user);
    
        return "user_profil";
    }
    
}
