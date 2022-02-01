    package com.app.ApiRestFul.controller;

import com.app.ApiRestFul.exceptions.RecordNotFoundException;
import com.app.ApiRestFul.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ApiRestFul.services.AdminService;
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
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService service;
	//antes de tocar esto tener el servicio hecho
        
	
        @GetMapping("/{id}")
        public ResponseEntity<Admin> getAdminById(@PathVariable("id") String id) 
                throws RecordNotFoundException{
            Admin admin=service.getAdminById(id);
            return new ResponseEntity<Admin>(admin, new HttpHeaders(),HttpStatus.OK);
        }
        
        @PostMapping
        public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin){
            Admin newAdmin=service.createAdmin(admin);      
            return new ResponseEntity<Admin>(newAdmin,new HttpHeaders(),HttpStatus.OK);
        }
        
        @PutMapping
        public ResponseEntity<Admin> UpdateAdmin(@RequestBody Admin admin)
                throws RecordNotFoundException{
            Admin newAdmin=service.createAdmin(admin);
            return new ResponseEntity<Admin>(newAdmin,new HttpHeaders(),HttpStatus.OK);
        }
        
        @DeleteMapping("/{id}")
        public HttpStatus deleteAdminById(@PathVariable("id") String id )
                throws RecordNotFoundException{
            service.deleteAdmin(id);
            return HttpStatus.ACCEPTED;
        }                    
        
}
