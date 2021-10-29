package com.paymybuddy.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.app.repository.TransactionRepository;


@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public void test () {
		
		
	}
	

}
