package com.paymybuddy.app.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name="transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	private User emitter;

	@OneToOne
	private User receiver;


	@Column(name="balance")
	@Range(min = 0, message = "Please enter an amount greater than 0.")
	private float balance;

	@Column(name="id_transaction")
	private String id_transaction;

	@Column(name="statut_transaction")
	private boolean statut_transaction;

	@Column(name="date_transaction")
	private LocalDateTime date_transaction;

	@Column(name="libelle_perso")
	private String libelle_perso;

	@Column(name="is_account_bank")
	private Boolean is_account_bank;

	@OneToOne
	private AccountBank accountBank; 

	/**
	 * @return the id_transaction
	 */
	public String getId_transaction() {
		return id_transaction;
	}


	/**
	 * @param string the id_transaction to set
	 */
	public void setId_transaction(String string) {
		this.id_transaction = string;
	}


	/**
	 * @return the accountBank
	 */
	public AccountBank getAccountBank() {
		return accountBank;
	}


	/**
	 * @param accountBank the accountBank to set
	 */
	public void setAccountBank(AccountBank accountBank) {
		this.accountBank = accountBank;
	}


	/**
	 * @return the is_account_bank
	 */
	public Boolean getIs_account_bank() {
		return is_account_bank;
	}


	/**
	 * @param is_account_bank the is_account_bank to set
	 */
	public void setIs_account_bank(Boolean is_account_bank) {
		this.is_account_bank = is_account_bank;
	}


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
	public LocalDateTime getDate_transaction() {
		return date_transaction;
	}


	/**
	 * @param localDate the date_transaction to set
	 */
	public void setDate_transaction(LocalDateTime localDate) {
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
