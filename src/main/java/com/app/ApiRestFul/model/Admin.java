package com.app.ApiRestFul.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "admin")
public class Admin {

	@Id
	@Column(name = "user")
	private String user;
	@Column(name = "password")
	private String password;
	@Column(name = "email")
	private String email;
	@JsonIgnoreProperties(value = {"admin"}, allowSetters = true)
	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
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
	
	

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	@Override
	public String toString() {
		return "Admin [user=" + user + ", password=" + password + ", email=" + email + ", clients=" + clients + "]";
	}
	
	
}
