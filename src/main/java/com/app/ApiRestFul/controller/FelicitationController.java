package com.app.ApiRestFul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ApiRestFul.services.FelicitationService;

@RestController
@RequestMapping("/Felicitation")
public class FelicitationController {

	@Autowired
	FelicitationService service;
	//antes de tocar esto tener el servicio hecho
}
