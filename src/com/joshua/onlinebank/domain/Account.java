package com.joshua.onlinebank.domain;
// Generated 2017-4-8 12:47:57 by Hibernate Tools 3.5.0.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Account generated by hbm2java
 */
public class Account implements java.io.Serializable {

	private Integer accountid;
	private Status status;
	private String username;
	private String password;
	private double balance;
	private Set transactionlogsForFromid = new HashSet(0);
	private Set personinfos = new HashSet(0);
	private Set transactionlogsForToid = new HashSet(0);

	public Account() {
	}

	public Account(Status status, String username, String password, double balance) {
		this.status = status;
		this.username = username;
		this.password = password;
		this.balance = balance;
	}

	public Account(Status status, String username, String password, double balance, Set transactionlogsForFromid,
			Set personinfos, Set transactionlogsForToid) {
		this.status = status;
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.transactionlogsForFromid = transactionlogsForFromid;
		this.personinfos = personinfos;
		this.transactionlogsForToid = transactionlogsForToid;
	}

	public Integer getAccountid() {
		return this.accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Set getTransactionlogsForFromid() {
		return this.transactionlogsForFromid;
	}

	public void setTransactionlogsForFromid(Set transactionlogsForFromid) {
		this.transactionlogsForFromid = transactionlogsForFromid;
	}

	public Set getPersoninfos() {
		return this.personinfos;
	}

	public void setPersoninfos(Set personinfos) {
		this.personinfos = personinfos;
	}

	public Set getTransactionlogsForToid() {
		return this.transactionlogsForToid;
	}

	public void setTransactionlogsForToid(Set transactionlogsForToid) {
		this.transactionlogsForToid = transactionlogsForToid;
	}

}
