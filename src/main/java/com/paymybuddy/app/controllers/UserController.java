package com.paymybuddy.app.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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


/*TODO
 * Remove les repositoru
 * +disso > controller service
 * Logger
 * */

@Controller
public class UserController {

	@Autowired
	private UserService userService;


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
		userService.saveUser(user);
		return "signup_success";
	}


	@GetMapping("/contact")
	public String userContact(Model model) {
		model.addAttribute("listUsers", userService.getContactUserConnected());
		return "user_contact";
	}


	@GetMapping("/profil")
	public String profil(Model model) {
		model.addAttribute("user", userService.getUserConnected(); );
		return "user_profil";
	}


	@GetMapping("/addFriend")
	public String addFriend(Model model){
		model.addAttribute("createFriendForm", new User());
		return "userContactCreate";
	}

	@PostMapping("/addFriend")
	public String formFriend (String email) {
		userService.addFriend(email);
		return "redirect:/contact";
	}


}
