package com.app.ApiRestFul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ApiRestFul.services.KidService;

@RestController
@RequestMapping("/Kid")
public class KidController {
	@Autowired
	KidService service;
}
