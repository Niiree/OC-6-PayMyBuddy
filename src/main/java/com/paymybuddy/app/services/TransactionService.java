package com.paymybuddy.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.app.models.Transaction;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repository.TransactionRepository;
import com.paymybuddy.app.repository.UserRepository;


@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void createTransaction(Transaction transaction) {
		 //User userRecipient = userRepository.findById(transaction.getId_user_recipient()).get();
		// User userSend = userRepository.findById(transaction.getId_user_spend()).get();
		 
		// if(userRecipient.getStatut_active() && userSend.getStatut_active()) {
		//	 if(userSend.getSolde() - transaction.getBalance() > 0) {
	//			userSend.setSolde(userSend.getSolde() - transaction.getBalance()); 
		//	 }
			 
			 
	//	 }
		 
		 
		
		
	}
	


}
