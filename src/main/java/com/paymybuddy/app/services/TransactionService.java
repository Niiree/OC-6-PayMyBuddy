package com.paymybuddy.app.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paymybuddy.app.models.Transaction;
import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repository.TransactionRepository;

import org.apache.logging.log4j.Logger;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private UserService userService;

	//Taxe sur chaque transaction entre les utilisateurs
	private double taxe = 0.05;

	private Logger logger;



	public Iterable<Transaction> findAll(){
		return transactionRepository.findAll();
	}

	public Transaction findById(int id) {
		Optional<Transaction> optional = transactionRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			logger.error("Transaction not found for id "+id);
			throw new RuntimeException();
		}
	}


	public Page<Transaction> pageFindAllByUserConnected(int one, int two,Boolean isBankAccount){
		User user = userService.getUserConnected();
		if(isBankAccount) {
			return transactionRepository.pageableFindAllBankTransactionByIdUser(user.getId(),PageRequest.of( one, two));	
		}
		return transactionRepository.pageableFindAllTransactionByIdUser(user.getId(),PageRequest.of( one, two));	
	}


	@Transactional
	public void createTransaction(Transaction transaction) {
		User emitter = userService.getUserConnected();
		transaction.setEmitter(emitter);
		User receiver = transaction.getReceiver();

		if(emitter != receiver && receiver.getStatut_active() ) {
			if(transaction.getBalance() > 0) {
				float taxeAmount = (float) (transaction.getBalance()* taxe);
				float transactionWithTaxe = transaction.getBalance()+taxeAmount;

				if(emitter.getBalance() - transactionWithTaxe > 0) {
					emitter.setBalance(emitter.getBalance()-transactionWithTaxe);
					receiver.setBalance(receiver.getBalance()+transaction.getBalance());
					transaction.setDate_transaction(LocalDateTime.now());
					transaction.setEmitter(emitter);
					transaction.setReceiver(receiver);
					transaction.setIs_account_bank(false);
					transaction.setStatut_transaction(true);
					transaction.setId_transaction(hashTransaction());
					transaction.setTaxe(taxeAmount);
					transactionRepository.save(transaction);	
				}
			}
		}
	}

	@Transactional
	public Transaction createTransactionBank(Transaction transaction) throws Exception {
		User user = userService.getUserConnected();

		if(user.getBalance() + transaction.getBalance() >= 0) {
			transaction.setId_transaction(hashTransaction());
			transaction.setDate_transaction(LocalDateTime.now());
			transaction.setEmitter(user);
			transaction.setReceiver(user);
			transaction.setIs_account_bank(true);
			transaction.setStatut_transaction(true);
			user.setBalance(user.getBalance() + transaction.getBalance());
			return transactionRepository.save(transaction);
		}else {

			return null;
		}

	}


	/*Hash de la transaction
	 * On vérifie bien que le hash fournis est unique
	 */
	private String hashTransaction() {
		StringBuilder s = new StringBuilder();
		List<Transaction> tr;
		try {

			do {
				String str = LocalDateTime.now().toString();
				MessageDigest msg = MessageDigest.getInstance("MD5");
				byte[] hash = msg.digest(str.getBytes(StandardCharsets.UTF_8));
				for (byte b : hash) {
					s.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
				}
				tr = transactionRepository.findByid_Transaction(s.toString());
			}while  (!tr.isEmpty());
		}catch (NoSuchAlgorithmException e){
			logger.error(e);
		}
		return s.toString();

	}
}
