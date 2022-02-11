package com.paymybuddy.app.controllers;



import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.paymybuddy.app.models.AccountBank;
import com.paymybuddy.app.models.Transaction;
import com.paymybuddy.app.repository.TransactionRepository;
import com.paymybuddy.app.services.AccountBankService;
import com.paymybuddy.app.services.TransactionService;
import com.paymybuddy.app.services.UserService;

@Controller
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private AccountBankService accountBankService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionRepository repository;
	
	
	@GetMapping("/transactions")
	public String transaction(Model model) {
		Iterable<Transaction> transactions = transactionService.findAll();
		Iterable<AccountBank> bank = accountBankService.findAll();
		
		model.addAttribute("transactions",transactions);
		model.addAttribute("bank",bank);
		return "transactionHistory";
	}
	
    @GetMapping("/createTransfer")
    public String transactionForm(Model model) {
        model.addAttribute("createTransferForm", new Transaction());
    	return "transactionCreate";
    }
    
    @GetMapping("/createTransferBank")
    public String transactionBankForm(Model model) {
        model.addAttribute("createTransferForm", new Transaction());
        model.addAttribute("banks",accountBankService.findAllAccountsByIdUser(userService.getUserConnected().getId()));
    	return "transactionBankCreate";
    }
    
    
    @PostMapping("/createTransferBank")
    public String submissionBankResult(@ModelAttribute("createTransferForm") Transaction transaction) throws Exception {
    		transactionService.createTransactionBank(transaction);
        return "redirect:/transactions";
}


    
    @PostMapping("/createTransfer")
        public String submissionResult(@ModelAttribute("createTransferForm") Transaction transaction) {
    		transaction.setDate_transaction(LocalDateTime.now());
    		transaction.setEmitter(userService.userByID(1));
    		transaction.setReceiver(userService.userByID(2));
        	repository.save(transaction);
            return "index";
    }
    


    
    
    
	
}
