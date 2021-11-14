package com.paymybuddy.app.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repository.UserRepository;



@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void saveUser(User user) {
		user.setDate_creation(LocalDate.now());
		//TODO vérification à faire email
		userRepository.save(user);
	}
	
	

}
