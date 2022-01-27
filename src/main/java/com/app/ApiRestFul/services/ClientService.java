package com.app.ApiRestFul.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.ApiRestFul.repository.ClientRepository;

public class ClientService {
	@Autowired
	ClientRepository repository;
}
