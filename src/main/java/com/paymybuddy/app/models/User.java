package com.paymybuddy.app.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	private float solde;
	
	@Column(name="date_creat")
	private Date date_creation;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String Password;
	
	@Column(name="statut_Active")
	private Boolean statut_active;
	
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name="id")
	private List<AccountBank> accountBanks = new ArrayList<>();
	
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
		return solde;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}

	public Date getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
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
