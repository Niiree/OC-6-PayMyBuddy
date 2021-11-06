package com.paymybuddy.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.app.models.Transaction;


@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer>{

}
