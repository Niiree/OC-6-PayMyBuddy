package com.paymybuddy.app.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.paymybuddy.app.models.AccountBank;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repository.AccountBankRepository;
import com.paymybuddy.app.repository.UserRepository;
import com.paymybuddy.app.services.AccountBankService;



@Controller
public class AccountBankController {
	
	@Autowired
	private AccountBankRepository accountBankRepository;
	
	@Autowired
	private AccountBankService accountBankService;
	
	@Autowired
	private UserRepository userRepository;
	
    @GetMapping("/accountBanks")
    public String accountBankList(Model model) {
    	int idUser = userRepository.findByEmail(nameUserConnect()).getId();
    	List<AccountBank> accounts = accountBankService.findAccountByIdUser(idUser);
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
    	accountBank.setUser(userRepository.findByEmail(nameUserConnect()));
    	//TODO Vérification si user exist
    	accountBankService.createUpdateAccountBank(accountBank);
        return "home";
    }
    
    private String nameUserConnect() {
    	Authentication authentification = SecurityContextHolder.getContext().getAuthentication();
    	return authentification.getName();
    }
    

	
}
