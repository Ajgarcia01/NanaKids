package com.app.ApiRestFul.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ApiRestFul.model.Client;
import com.app.ApiRestFul.model.Kid;
import com.app.ApiRestFul.services.KidService;

@RestController
@RequestMapping("/kid")
public class KidController {
	@Autowired
	KidService service;
	//antes de tocar esto tener el servicio hecho
	
	@GetMapping
	public ResponseEntity<List<Kid>> getAllKids(){
		try {
			List<Kid> all= service.getAllKids();
			return new ResponseEntity<List<Kid>>(all,new HttpHeaders(),HttpStatus.OK);
		} catch (Exception e) {
			List<Kid> all= service.getAllKids();
			return new ResponseEntity<List<Kid>>(all,new HttpHeaders(),HttpStatus.OK);
		}
		
		
	}
	
	@GetMapping("/clients/{id}")
	public ResponseEntity<List<Client>> getClientsByKid(@PathVariable("id") Long id){
		if(id!=null && id > -1) {
			try {
				List<Client> all= service.getClientsByKid(id);
				return new ResponseEntity<List<Client>>(all,new HttpHeaders(),HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<List<Client>>(new HttpHeaders(),HttpStatus.NOT_FOUND);
			}
			
		}else {
			return new ResponseEntity<List<Client>>(new HttpHeaders(),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/search/{name}")
	public ResponseEntity<List<Kid>> getKidByName(@PathVariable("name") String name){
		List<Kid> all= service.getKidByName(name);
		return new ResponseEntity<List<Kid>>(all,new HttpHeaders(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Kid> getKidById(@PathVariable("id") Long id){
		Kid note = service.getNotesById(id);
		return new ResponseEntity<Kid>(note,new HttpHeaders(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Kid> createKid(@RequestBody Kid n){
		Kid note = service.createClient(n);
		return new ResponseEntity<Kid>(note,new HttpHeaders(),HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Kid> UpdateKid(@RequestBody Kid n){
		Kid note = service.Update(n);
		return new ResponseEntity<Kid>(note,new HttpHeaders(),HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public HttpStatus deleteNote(@PathVariable("id") Long id){
		service.deleteKidById(id);
		return HttpStatus.OK;
	}
}
