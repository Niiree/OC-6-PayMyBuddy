package com.paymybuddy.app.services;

import java.time.LocalDate;
import java.util.List;
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
	
	
	public Iterable<Transaction> findAll(){
		return transactionRepository.findAll();
	}
	
	public Optional<Transaction> findById() {
		
		return transactionRepository.findById(1);
	}
	
	@Transactional
	public Transaction createTransaction(Transaction transaction) throws Exception {
		User emitter = transaction.getEmitter();
		User receiver = transaction.getReceiver();
		
		if(emitter != receiver) {
			if(receiver.getStatut_active()) {
				if(emitter.getBalance() - transaction.getBalance() > 0) {
					emitter.setBalance(emitter.getBalance()-transaction.getBalance());
					receiver.setBalance(receiver.getBalance()+transaction.getBalance());
					transaction.setDate_transaction(LocalDate.now());
					transaction.setEmitter(emitter);
					transaction.setReceiver(receiver);
					transaction.setStatut_transaction(true);
					return transactionRepository.save(transaction);
				
				}else {
					throw new Exception("Not enough money");
				}	
			}else {
				throw new Exception ("User receiver was not active");
			}
		}else {
			throw new Exception ("Same user");
		}
		
		
		
	}
		 
		 
		
		
	
	


}
