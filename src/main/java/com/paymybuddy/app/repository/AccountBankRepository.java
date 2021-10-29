package com.paymybuddy.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.paymybuddy.app.models.AccountBank;


public interface AccountBankRepository extends CrudRepository<AccountBank, Integer> {

}
