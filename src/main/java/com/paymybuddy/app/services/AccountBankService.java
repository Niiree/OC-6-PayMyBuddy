package com.paymybuddy.app.services;

import java.util.List;
import java.util.Optional;

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
			accountBankFind.setAddress(accountBank.getAddress());
			accountBankFind.setCity(accountBank.getCity());
			accountBankFind.setName(accountBank.getName());
			accountBankFind.setZip(accountBank.getZip());
			return accountBankRepository.save(accountBankFind);
		}else {
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
	
	public List<AccountBank> findAccountByIdUser(int id){
	
	List<AccountBank> account = accountBankRepository.findAllByIdUser(id);
	return account;
	}
	
	
}
