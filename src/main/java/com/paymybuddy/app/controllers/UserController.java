package com.paymybuddy.app.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paymybuddy.app.models.Transaction;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.services.TransactionService;
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
	private TransactionService transactionService;

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
	public String processRegister(@Valid User user,BindingResult result) {
		user = userService.findByEmail(user.getEmail());
		if(user != null ) {
			result.rejectValue("email", "error.email","You cannot use this email !");
			return "signup_form";
		}else if(result.hasErrors()) {
		return "signup_form";	
		}
		userService.saveUser(user);
		return "signup_success";
	}


	@GetMapping("/contact")
	public String userContact(Model model) {
		model.addAttribute("listUsers", userService.getContactUserConnected());
		return "user_contact";
	}


	@GetMapping("/profil")
	public String profil(Model model,@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(6);

	    Page<Transaction> transactions = transactionService.pageFindAllByUserConnected(currentPage-1,pageSize,true);
	    int totalPages = transactions.getTotalPages();
		model.addAttribute("totalPages",totalPages);
		
        List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
        model.addAttribute("pageNumbers", pageNumbers);
 

		model.addAttribute("transactions",transactions);
		
		model.addAttribute("user", userService.getUserConnected());
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
