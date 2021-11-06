package com.paymybuddy.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.app.repository.AccountBankRepository;

import com.paymybuddy.app.models.AccountBank;

@Service
public class AccountBankService {
	
	@Autowired
	private AccountBankRepository accountBankRepository;
	
	public Iterable<AccountBank> findAll(){
		return accountBankRepository.findAll();
	}
	
	
	public AccountBank createUpdateAccountBank(AccountBank accountBank) {
		AccountBank accountBankFind = accountBankRepository.findByIban(accountBank.getIban());
		if(accountBankFind != null) {
			accountBankFind.setAddress(null);
			accountBankFind.setCity(null);
			accountBankFind.setName(accountBank.getName());
			accountBankFind.setZip(accountBank.getZip());
			return accountBankRepository.save(accountBankFind);
		}else {
			return accountBankRepository.save(accountBankFind);
		}
	}
	
	public void deleteAccountBank(AccountBank accountBank) throws Exception {
		AccountBank accountBankFind = accountBankRepository.findByIban(accountBank.getIban());
		if(accountBankFind!=null) {
			accountBankRepository.delete(accountBankFind);
		}else{
			throw new Exception("Account not find");
		}
	}
}
