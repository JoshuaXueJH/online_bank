package com.joshua.onlinebank.domain;
// Generated 2017-4-8 12:47:57 by Hibernate Tools 3.5.0.Final

/**
 * Admin generated by hbm2java
 */
public class Admin implements java.io.Serializable {

	private Integer id;
	private String username;
	private String password;

	public Admin() {
	}

	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}
