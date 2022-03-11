package com.paymybuddy.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.app.models.Transaction;


@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer>{

	//Return une transaction : ID Transaction
	@Query(value = "SELECT * FROM transaction u WHERE u.id_transaction = ?1", nativeQuery = true)
	public List<Transaction> findByid_Transaction(String str);

	@Query(value = "SELECT * FROM transaction u WHERE u.emitter_id = ?1 or u.receiver_id = ?1", nativeQuery = true)
	public List<Transaction> findAllTransactionByIdUser(int id);

	
}
