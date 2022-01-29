package com.paymybuddy.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.app.models.AccountBank;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repository.AccountBankRepository;

@Service
public class AccountBankService {
	
	@Autowired
	private AccountBankRepository accountBankRepository;
	
	@Autowired
	private UserService userService;
	
	public Iterable<AccountBank> findAll(){
		return accountBankRepository.findAll();
	}
	
	
	public AccountBank createUpdateAccountBank(AccountBank accountBank) {
		User user = userService.getUserConnected(); //TODO Vérifier proprietaire du compte bank
		
		AccountBank accountBankFind = accountBankRepository.findByIban(accountBank.getIban());
		if(accountBankFind != null) {
			accountBankFind.setAddress(accountBank.getAddress());
			accountBankFind.setCity(accountBank.getCity());
			accountBankFind.setName(accountBank.getName());
			accountBankFind.setZip(accountBank.getZip());
			return accountBankRepository.save(accountBankFind);
		}else {
			accountBank.setUser(user);
			accountBank.setStatutActive(true);
			return accountBankRepository.save(accountBank);
		}
	}
	
	public void deleteAccountBank(AccountBank accountBank) throws Exception {
		AccountBank accountBankFind = accountBankRepository.findByIban(accountBank.getIban());
		if(accountBankFind!=null) {
			accountBankFind.setStatutActive(false);
			accountBankRepository.save(accountBankFind);
		}else{
			throw new Exception("Account not find");
		}
	}
	
	public List<AccountBank> findAllAccountsByIdUser(int id){
	List<AccountBank> account = accountBankRepository.findAllByIdUser(id);
	return account;
	}
	

	
	
}
