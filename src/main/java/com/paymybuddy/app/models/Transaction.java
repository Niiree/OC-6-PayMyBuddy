package com.paymybuddy.app.models;

import java.time.LocalDate;
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
	private User emitter;
	
	@OneToOne
	private User receiver;
	
	@Column(name="balance")
	private float balance;
	
	@Column(name="statut_transaction")
	private boolean statut_transaction;
	
	@Column(name="date_transaction")
	private LocalDate date_transaction;
	
	@Column(name="libelle_perso")
	private String libelle_perso;


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
	public User getEmitter() {
		return emitter;
	}


	/**
	 * @param sender the sender to set
	 */
	public void setEmitter(User emitter) {
		this.emitter = emitter;
	}


	/**
	 * @return the recipient
	 */
	public User getReceiver() {
		return receiver;
	}


	/**
	 * @param recipient the recipient to set
	 */
	public void setReceiver(User receiver) {
		this.receiver = receiver;
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
	public LocalDate getDate_transaction() {
		return date_transaction;
	}


	/**
	 * @param localDate the date_transaction to set
	 */
	public void setDate_transaction(LocalDate localDate) {
		this.date_transaction = localDate;
	}


	/**
	 * @return the libelle_perso
	 */
	public String getLibelle_perso() {
		return libelle_perso;
	}


	/**
	 * @param libelle_perso the lieblle_perso to set
	 */
	public void setLibelle_perso(String libelle_perso) {
		this.libelle_perso = libelle_perso;
	}
	
	
	

	
}
