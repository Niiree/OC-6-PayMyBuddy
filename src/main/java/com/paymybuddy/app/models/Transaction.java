package com.paymybuddy.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="id_user")
	private int id_user;
	
	@Column(name="id_contact")
	private int id_contact;
	
	@Column(name="id_bank")
	private int id_bank;

	public int getId() {
		return id;
	}
	
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public int getId_contact() {
		return id_contact;
	}
	public void setId_contact(int id_contact) {
		this.id_contact = id_contact;
	}
	public int getId_bank() {
		return id_bank;
	}
	public void setId_bank(int id_bank) {
		this.id_bank = id_bank;
	}

}
