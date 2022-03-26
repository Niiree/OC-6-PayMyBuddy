package com.paymybuddy.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.paymybuddy.app.models.User;


@Repository
public interface UserRepository extends JpaRepository <User, Integer> {
	
	//return un utilisateur par son email 
	 @Query("SELECT u FROM User u WHERE u.email = ?1")
	    public User findByEmail(String email);
	 

}
