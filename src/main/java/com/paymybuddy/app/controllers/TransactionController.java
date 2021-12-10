package com.paymybuddy.app.controllers;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paymybuddy.app.models.Transaction;
import com.paymybuddy.app.services.TransactionService;

@Controller
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	
	@RequestMapping("/transactions")
	public String transaction(Model model) {
		Iterable<Transaction> transactions = transactionService.findAll();
		model.addAttribute("transactions",transactions);
		return "transaction_history";
	}
	
    @RequestMapping("/transfer")
    public String transfer() {
    	return "layouts/transfer";
    }
	
}
