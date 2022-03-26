package com.paymybuddy.app.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
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

	
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(AccountBankService.class);

	public Iterable<AccountBank> findAll(){
		return accountBankRepository.findAll();
	}


	public AccountBank createUpdateAccountBank(AccountBank accountBank) {
		User user = userService.getUserConnected(); 
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

	public void disableAccountBank(int id)  {
		Optional<AccountBank> accountBankFind = accountBankRepository.findById(id);
		User user = userService.getUserConnected();

		if(accountBankFind.isPresent()) {
			if(accountBankFind.get().getUser().getId() == user.getId()) {
				accountBankFind.get().setStatutActive(false);
				accountBankRepository.save(accountBankFind.get());
			}else {
				logger.error("User id != acount user id");
			}
		}else{
			logger.error("Account not found");
		}
	}

	public List<AccountBank> findAllAccountsByIdUser(int id){
		List<AccountBank> account = accountBankRepository.findAllByIdUser(id);
		return account;
	}

}
