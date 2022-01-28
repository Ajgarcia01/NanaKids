package com.app.ApiRestFul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ApiRestFul.services.ClientService;

@RestController
@RequestMapping("/Client")
public class ClientController {

	@Autowired
	ClientService service;
	//antes de tocar esto tener el servicio hecho
}
