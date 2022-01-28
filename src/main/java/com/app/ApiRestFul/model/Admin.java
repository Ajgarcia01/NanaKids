package com.app.ApiRestFul.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Admin {

	@Id
	@Column(name = "user")
	private String user;
	@Column(name = "password")
	private String password;
	@Column(name = "email")
	private String email;
	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Client> clients;

	public Admin() {

	}

	public Admin(Long id, String user, String password, String email) {
		this.user = user;
		this.password = password;
		this.email = email;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Admin [user=" + user + ", password=" + password + ", email=" + email + ", clients=" + clients + "]";
	}
	
	
}
