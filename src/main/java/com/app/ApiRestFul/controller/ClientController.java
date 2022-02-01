package com.app.ApiRestFul.controller;

import com.app.ApiRestFul.exceptions.RecordNotFoundException;
import com.app.ApiRestFul.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ApiRestFul.services.ClientService;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/Client")
public class ClientController {

	@Autowired
	ClientService service;
	//antes de tocar esto tener el servicio hecho
        
         
        @GetMapping
        public ResponseEntity<List<Client>> getAllClient(){
            List<Client> list=service.getAllClient();
            return new ResponseEntity<List<Client>>(list,new HttpHeaders(),HttpStatus.OK);
        }
        
        @GetMapping("/{id}")
        public ResponseEntity<Client> getClientById(@PathVariable("id") String id) 
                throws RecordNotFoundException{
            Client admin=service.getClientById(id);
            return new ResponseEntity<Client>(admin, new HttpHeaders(),HttpStatus.OK);
        }
        
        @PostMapping
        public ResponseEntity<Client> createClient(@RequestBody Client client){
            Client newClient=service.createClient(client);      
            return new ResponseEntity<Client>(newClient,new HttpHeaders(),HttpStatus.OK);
        }
        
        @PutMapping
        public ResponseEntity<Client> UpdateClient(@RequestBody Client client)
                throws RecordNotFoundException{
            Client newClient=service.createClient(client);
            return new ResponseEntity<Client>(newClient,new HttpHeaders(),HttpStatus.OK);
        }
        
        @DeleteMapping("/{id}")
        public HttpStatus deleteClientById(@PathVariable("id") String id )
                throws RecordNotFoundException{
            service.deleteClient(id);
            return HttpStatus.ACCEPTED;
        }
                
}
