package com.paymybuddy.app.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.app.models.Transaction;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repository.TransactionRepository;


@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	
	@Autowired
	private UserService userService;
	
	
	public Iterable<Transaction> findAll(){
		return transactionRepository.findAll();
	}
	
	public Transaction findById(int id) {
		 Optional<Transaction> optional = transactionRepository.findById(id);
		 if(optional.isPresent()) {
			 return optional.get();
		 }else {
			 throw new RuntimeException("Transaction not found for id "+id);
		 }
	}
	
	@Transactional
	public Transaction createTransaction(Transaction transaction) throws Exception {
		User emitter = userService.getUserConnected();
		transaction.setEmitter(emitter);
		User receiver = transaction.getReceiver();
		
		if(emitter != receiver) {
			if(receiver.getStatut_active()) {
				if(emitter.getBalance() - transaction.getBalance() > 0) {
					emitter.setBalance(emitter.getBalance()-transaction.getBalance());
					receiver.setBalance(receiver.getBalance()+transaction.getBalance());
					transaction.setDate_transaction(LocalDateTime.now());
					transaction.setEmitter(emitter);
					transaction.setReceiver(receiver);
					transaction.setIs_account_bank(false);
					transaction.setStatut_transaction(true);
					transaction.setId_transaction(hashTransaction());
					
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
		 
	@Transactional
	public Transaction createTransactionBank(Transaction transaction) throws Exception {
		User user = userService.getUserConnected();
		if(user.getBalance() + transaction.getBalance() > 0) {
			transaction.setId_transaction(hashTransaction());
			transaction.setDate_transaction(LocalDateTime.now());
			transaction.setEmitter(user);
			transaction.setReceiver(user);
			transaction.setIs_account_bank(true);
			transaction.setStatut_transaction(true);
			user.setBalance(user.getBalance() + transaction.getBalance());
			return transactionRepository.save(transaction);
		}else {
			throw new Exception("Not enough money on account");
		}
		
	}
	
	private String hashTransaction() throws NoSuchAlgorithmException {
		 StringBuilder s = new StringBuilder();
		List<Transaction> tr;
		do {
        String str = LocalDateTime.now().toString();
        MessageDigest msg = MessageDigest.getInstance("MD5");
        byte[] hash = msg.digest(str.getBytes(StandardCharsets.UTF_8));
        for (byte b : hash) {
            s.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        tr = transactionRepository.findByid_Transaction(s.toString());
		}while  (!tr.isEmpty());
        
        
        return s.toString();
		
	}


}
