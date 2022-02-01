package com.app.ApiRestFul.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="client")
public class Client {

	//private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "Type")
	private boolean type; //true padre, false madre
	@Column(name = "Name")
	private String Name;
	@Column(name = "Surname")
	private String Surname;
	@Column(name = "Phone",length = 9)
	private int Phone;
	@Column(name = "Email")
	private String Email;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Kid> kids;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_admin")
	private Admin admin;
	
	
	public Client(){
	
	}

	public Client(String id, boolean type, String name, String surname, int phone, String email) {
		this.id = id;
		this.type = type;
		this.Name = name;
		this.Surname = surname;
		this.Phone = phone;
		this.Email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	public int getPhone() {
		return Phone;
	}

	public void setPhone(int phone) {
		Phone = phone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", type=" + type + ", Name=" + Name + ", Surname=" + Surname + ", Phone=" + Phone
				+ ", Email=" + Email + ", kids=" + kids + ", admin=" + admin + "]";
	}
	
	
}
