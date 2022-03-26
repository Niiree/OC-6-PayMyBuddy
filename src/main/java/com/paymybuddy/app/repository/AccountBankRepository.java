package com.paymybuddy.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.app.models.AccountBank;


@Repository
public interface AccountBankRepository extends CrudRepository<AccountBank, Integer> {


	AccountBank findByIban(String iban);
	// Return tout les AccountBank sous statut d'un utilisateur :ID User
	@Query(value = "SELECT * FROM account_bank u WHERE u.user_id = ?1 AND u.statut_active = true", nativeQuery = true)
	public List<AccountBank> findAllByIdUser(int id);


}
