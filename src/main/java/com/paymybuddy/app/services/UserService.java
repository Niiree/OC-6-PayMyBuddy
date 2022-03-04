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

	

	public User getUserConnected() {
		Authentication authentification = SecurityContextHolder.getContext().getAuthentication();
		return userRepository.findByEmail(authentification.getName());	    
	} 
	
	public addFriend(String email) {
		User userConnected = userService.getUserConnected();
		User userFind = userRepo.findByEmail(email);
		//Vérification si l'utilisateur ajouté existe + pas celui connecté
		if((userFind != null) && (userFind != userConnected)) {
			Set<User> set = userConnected.getContact();
			set.add(userFind);
			userConnected.setContact(set);
		}
		userRepo.save(userConnected);	
	}
	
	//Contact de l'utilisateur connecté
	public Set<USer> getContactUserConnected() {
		User userConnected = userService.getUserConnected();
		Set<User> userContact = userConnected.getContact();
		Set<User> listUsers = userContact;
		return listUsers;
	}
	
	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();	
	}

}
