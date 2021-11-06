package com.paymybuddy.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.app.models.AccountBank;


@Repository
public interface AccountBankRepository extends CrudRepository<AccountBank, Integer> {
	
	
	AccountBank findByIban(String iban);


}
