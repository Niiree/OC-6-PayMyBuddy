package com.paymybuddy.app.controllers;



import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paymybuddy.app.models.AccountBank;
import com.paymybuddy.app.models.Transaction;
import com.paymybuddy.app.repository.TransactionRepository;
import com.paymybuddy.app.services.TransactionService;
import com.paymybuddy.app.services.UserService;

@Controller
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionRepository repository;
	
	
	@GetMapping("/transactions")
	public String transaction(Model model) {
		Iterable<Transaction> transactions = transactionService.findAll();
		model.addAttribute("transactions",transactions);
		return "transactionHistory";
	}
	
    @GetMapping("/createTransfer")
    public String transactionForm(Model model) {
        model.addAttribute("createTransferForm", new Transaction());
    	return "transactionCreate";
    }
    
    @PostMapping("/createTransfer")
        public String submissionResult(@ModelAttribute("createTransferForm") Transaction transaction) {
    		transaction.setDate_transaction(LocalDate.now());
    		//TODO Test en cours
    		transaction.setEmitter(userService.userByID(1));
    		transaction.setReceiver(userService.userByID(2));
        	repository.save(transaction);
            return "index";
    }
	
}
