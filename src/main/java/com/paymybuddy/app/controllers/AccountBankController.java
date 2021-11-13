package com.paymybuddy.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.paymybuddy.app.models.AccountBank;
import com.paymybuddy.app.repository.AccountBankRepository;



@Controller
public class AccountBankController {

	
	@Autowired
	private AccountBankRepository acb;
	
	
    @GetMapping("/accountBank")
    public String friendForm(Model model) {
        model.addAttribute("personForm", new AccountBank());
        return "NewFile";
    }

    @PostMapping("/accountBank")
    public String submissionResult(@ModelAttribute("personForm") AccountBank accountBank) {
    	acb.save(accountBank);//temporaire test TODO
        return "result";
    }
	
	
}
