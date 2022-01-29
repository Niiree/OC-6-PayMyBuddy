package com.paymybuddy.app.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.paymybuddy.app.models.AccountBank;
import com.paymybuddy.app.services.AccountBankService;
import com.paymybuddy.app.services.UserService;



@Controller
public class AccountBankController {
	
	@Autowired
	private AccountBankService accountBankService;
	
	@Autowired
	private UserService userService;
	
    @GetMapping("/accountBanks")
    public String accountBankList(Model model) {
    	int idUser = userService.getUserConnected().getId();
    	List<AccountBank> accounts = accountBankService.findAllAccountsByIdUser(idUser);
    	if(!accounts.isEmpty()) {
    		model.addAttribute("accounts",accounts);
    	}
    	
    	return "accountBankList";
    }
	
	
	@GetMapping("/createAccountBank")
    public String accountBankForm(Model model) {
        model.addAttribute("accountBankForm", new AccountBank());
        
        return "createAccountBank";
    }

    @PostMapping("/accountBank")
    public String submissionResult(@ModelAttribute("personForm") AccountBank accountBank,HttpServletRequest request) {
    	accountBankService.createUpdateAccountBank(accountBank);
        return "home";
    }
    

    

	
}
