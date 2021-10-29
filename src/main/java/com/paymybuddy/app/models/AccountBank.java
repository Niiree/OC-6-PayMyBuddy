package com.paymybuddy.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="account_bank")
public class AccountBank {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name_bank")
	private String nameBank;
	
	@Column(name="iban_bank")
	private String ibanBank;
	
	public int getId() {
		return id;
	}

	
	public String getNameBank() {
		return nameBank;
	}
	public void setNameBank(String nameBank) {
		this.nameBank = nameBank;
	}
	public String getIbanBank() {
		return ibanBank;
	}
	public void setIbanBank(String ibanBank) {
		this.ibanBank = ibanBank;
	}


}
