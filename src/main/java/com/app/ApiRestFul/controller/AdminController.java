package com.app.ApiRestFul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ApiRestFul.services.AdminService;

@RestController
@RequestMapping("/Admin")
public class AdminController {
	@Autowired
	AdminService service;
	
	//antes de tocar esto tener el servicio hecho
}
