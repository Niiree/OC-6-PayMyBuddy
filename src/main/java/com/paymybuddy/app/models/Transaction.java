package com.paymybuddy.app.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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


	@Column(name="taxe")
	private double taxe;


	public double getTaxe() {
		return taxe;
	}


	public void setTaxe(double taxe) {
		this.taxe = taxe;
	}


	public String getId_transaction() {
		return id_transaction;
	}


	public void setId_transaction(String string) {
		this.id_transaction = string;
	}

	public AccountBank getAccountBank() {
		return accountBank;
	}


	public void setAccountBank(AccountBank accountBank) {
		this.accountBank = accountBank;
	}


	public Boolean getIs_account_bank() {
		return is_account_bank;
	}



	public void setIs_account_bank(Boolean is_account_bank) {
		this.is_account_bank = is_account_bank;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public User getEmitter() {
		return emitter;
	}


	public void setEmitter(User emitter) {
		this.emitter = emitter;
	}


	public User getReceiver() {
		return receiver;
	}


	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}



	public float getBalance() {
		return balance;
	}


	public void setBalance(float balance) {
		this.balance = balance;
	}

	public boolean isStatut_transaction() {
		return statut_transaction;
	}

	public void setStatut_transaction(boolean statut_transaction) {
		this.statut_transaction = statut_transaction;
	}

	public LocalDateTime getDate_transaction() {
		return date_transaction;
	}

	public void setDate_transaction(LocalDateTime localDate) {
		this.date_transaction = localDate;
	}

	public String getLibelle_perso() {
		return libelle_perso;
	}

	public void setLibelle_perso(String libelle_perso) {
		this.libelle_perso = libelle_perso;
	}


}
