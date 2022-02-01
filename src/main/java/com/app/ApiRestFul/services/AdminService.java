package com.app.ApiRestFul.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ApiRestFul.repository.AdminRepository;

@Service
public class AdminService {
	@Autowired
	AdminRepository repository;
}
