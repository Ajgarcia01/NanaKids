package com.app.ApiRestFul.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class Client {

	//private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "DNI", length = 8)
	private Long DNI;
	@Column(name = "Type")
	private boolean type;
	@Column(name = "Name")
	private String Name;
	@Column(name = "Surname")
	private String Surname;
	@Column(name = "Phone",length = 9)
	private int Phone;
	@Column(name = "Email")
	private String Email;
	
	public Client(){
	
	}

	public Client(Long DNI, boolean type, String name, String surname, int phone, String email) {
		this.DNI = DNI;
		this.type = type;
		this.Name = name;
		this.Surname = surname;
		this.Phone = phone;
		this.Email = email;
	}

	public Long getDNI() {
		return DNI;
	}

	public void setDNI(Long dNI) {
		DNI = dNI;
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
}
