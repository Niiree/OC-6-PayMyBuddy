package com.paymybuddy.app.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.paymybuddy.app.models.AccountBank;
import com.paymybuddy.app.models.Transaction;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.services.AccountBankService;
import com.paymybuddy.app.services.TransactionService;
import com.paymybuddy.app.services.UserService;

/*TODO
 * Remove les repositoru
 * +disso > controller service
 * Logger
 * */

@Controller
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private AccountBankService accountBankService;

	@Autowired
	private UserService userService;

	@GetMapping("/transactions")
	public String transaction(Model model) {
		Iterable<Transaction> transactionsList = transactionService.findAllByUserConnected();
		Iterable<AccountBank> bankList = accountBankService.findAll();
		model.addAttribute("transactions",transactionsList);
		model.addAttribute("bank",bankList);
		return "transactionHistory";
	}

	@GetMapping("/createTransfer")
	public String transactionForm(Model model) {
		model.addAttribute("createTransferForm",new Transaction());
		List<User> userList = new ArrayList<>(userService.getUserConnected().getContact());
		model.addAttribute("users",userList);
		return "transactionCreate";
	}

	@PostMapping("/createTransfer")
	public String submissionResult(@ModelAttribute("createTransferForm") Transaction transaction) throws Exception {
		transactionService.createTransaction(transaction);
		return "redirect:/transactions";
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


}
