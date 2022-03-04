package com.paymybuddy.app.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.paymybuddy.app.models.User;
import com.paymybuddy.app.repository.UserRepository;



@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;



	public void saveUser(User user) {
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		user.setStatut_active(true);
		user.setDate_creation(LocalDateTime.now());
		userRepository.save(user);
	}

	public User userByID(int id) {
		return userRepository.getById(id);
	}

	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();	
	}

	public User getUserConnected() {
		Authentication authentification = SecurityContextHolder.getContext().getAuthentication();
		return userRepository.findByEmail(authentification.getName());	    
	} 

}
