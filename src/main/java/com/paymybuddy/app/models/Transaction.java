package com.paymybuddy.app.models;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="transaction")
//@Access(AccessType.PROPERTY)
public class Transaction {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	private User sender;
	
	@OneToOne
	private User recipient;
	
	@Column(name="balance")
	private float balance;
	
	@Column(name="statut_transaction")
	private boolean statut_transaction;
	
	@Column(name="date_transaction")
	private Date date_transaction;
	
	@Column(name="libelle_perso")
	private String lieblle_perso;


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the sender
	 */
	public User getSender() {
		return sender;
	}


	/**
	 * @param sender the sender to set
	 */
	public void setSender(User sender) {
		this.sender = sender;
	}


	/**
	 * @return the recipient
	 */
	public User getRecipient() {
		return recipient;
	}


	/**
	 * @param recipient the recipient to set
	 */
	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}


	/**
	 * @return the balance
	 */
	public float getBalance() {
		return balance;
	}


	/**
	 * @param balance the balance to set
	 */
	public void setBalance(float balance) {
		this.balance = balance;
	}


	/**
	 * @return the statut_transaction
	 */
	public boolean isStatut_transaction() {
		return statut_transaction;
	}


	/**
	 * @param statut_transaction the statut_transaction to set
	 */
	public void setStatut_transaction(boolean statut_transaction) {
		this.statut_transaction = statut_transaction;
	}


	/**
	 * @return the date_transaction
	 */
	public Date getDate_transaction() {
		return date_transaction;
	}


	/**
	 * @param date_transaction the date_transaction to set
	 */
	public void setDate_transaction(Date date_transaction) {
		this.date_transaction = date_transaction;
	}


	/**
	 * @return the lieblle_perso
	 */
	public String getLieblle_perso() {
		return lieblle_perso;
	}


	/**
	 * @param lieblle_perso the lieblle_perso to set
	 */
	public void setLieblle_perso(String lieblle_perso) {
		this.lieblle_perso = lieblle_perso;
	}
	

	

	
}
