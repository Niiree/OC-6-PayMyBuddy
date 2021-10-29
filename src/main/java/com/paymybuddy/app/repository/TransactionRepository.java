package com.paymybuddy.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.paymybuddy.app.models.Transaction;


public interface TransactionRepository extends CrudRepository<Transaction, Integer>{

}
