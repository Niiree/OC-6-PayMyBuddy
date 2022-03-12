package com.paymybuddy.app.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.*;

import org.hibernate.annotations.ValueGenerationType;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="firstname")
	private String firstName;

	@Column(name="lastname")
	private String lastName;

	@Column(name="balance")
	private float balance;

	@Column(name="date_creat")
	private LocalDateTime date_creation;

	@Column(name="email", unique = true)
	private String email;

	@Column(name="password")
	private String Password;

	@Column(name="statut_Active")
	private Boolean statut_active; //TODO A REFACTOR statut_IsActive


	
    @JoinTable(name="connections", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    }, inverseJoinColumns = {
            @JoinColumn(name = "friend_id", referencedColumnName = "id", nullable = false)
    })
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> friendlist;





	/**
	 * @return the listFriend
	 */
	public List<User> getListFriend() {
		return friendlist;
	}


	/**
	 * @param listFriend the listFriend to set
	 */
	public void setListFriend(List<User> listFriend) {
		this.friendlist = listFriend;
	}


	public int getId() {
		return id;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public LocalDateTime getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(LocalDateTime localDate) {
		this.date_creation = localDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}


	/**
	 * @return the statut_active
	 */
	public Boolean getStatut_active() {
		return statut_active;
	}


	/**
	 * @param statut_active the statut_active to set
	 */
	public void setStatut_active(Boolean statut_active) {
		this.statut_active = statut_active;
	}	

}

