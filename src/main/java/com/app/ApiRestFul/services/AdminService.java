package com.app.ApiRestFul.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.ApiRestFul.repository.AdminRepository;

public class AdminService {
	@Autowired
	AdminRepository repository;
}
