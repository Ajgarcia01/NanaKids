package com.app.ApiRestFul.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ApiRestFul.repository.ClientRepository;

@Service
public class ClientService {
	@Autowired
	ClientRepository repository;
}
