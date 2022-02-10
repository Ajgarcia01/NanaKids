package com.app.ApiRestFul.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ApiRestFul.exceptions.RecordNotFoundException;
import com.app.ApiRestFul.model.Felicitation;
import com.app.ApiRestFul.services.FelicitationService;

@RestController
@RequestMapping("/felicitation")
public class FelicitationController {

	@Autowired
	FelicitationService service;
	// antes de tocar esto tener el servicio hecho
	
	//-----------------GET

	@GetMapping
	public ResponseEntity<List<Felicitation>> getAllFelicitations() {
		List<Felicitation> list = service.getAllFelicitations();
		return new ResponseEntity<List<Felicitation>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	//Get felicitation by ID
	
	@GetMapping("/{id}")
	public ResponseEntity<Felicitation> getItemById(@PathVariable("id") Long id) throws RecordNotFoundException {
		
		Felicitation entity = service.getFelicitationById(id);

		return new ResponseEntity<Felicitation>(entity, new HttpHeaders(), HttpStatus.OK);
	}
	
	//Get ListFelicitation by type
	@GetMapping("/search/{type}")
	public ResponseEntity<List<Felicitation>> getListByType( @PathVariable("type")int type){
		List<Felicitation> list  =  service.listTypeFelicitation(type);
		return new ResponseEntity<List<Felicitation>>(list , new HttpHeaders() , HttpStatus.OK);
	}
	
	//OBTENER EL NUMERO DE FELICITACIONES TOTALES
	
	@GetMapping("/count")
	public ResponseEntity<Long> getCount(){
		Long count = service.numberFelicitations();
		return new ResponseEntity<Long>(count , new HttpHeaders() , HttpStatus.OK);
	}
	
	 
	//-----------------------DELETE
	 @DeleteMapping("/{id}")
	    public HttpStatus deleteItemById(@PathVariable("id") Long id) throws RecordNotFoundException {
	        service.deleteFelicitation(id);
	        return HttpStatus.OK;
	    }
	
	

	
	

}
