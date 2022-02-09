package com.app.ApiRestFul.controller;

import com.app.ApiRestFul.exceptions.RecordNotFoundException;
import com.app.ApiRestFul.model.Admin;
import com.app.ApiRestFul.model.Client;

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

    @GetMapping
    public ResponseEntity<List<Admin>> getAllClient() {
        List<Admin> list = service.getAllAdmin();
        return new ResponseEntity<List<Admin>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable("id") String id)
            throws RecordNotFoundException {
        if (id != null) {
            try {
                Admin admin = service.getAdminById(id);
                return new ResponseEntity<Admin>(admin, new HttpHeaders(), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<Admin>(new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<Admin>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        if (admin != null) {
            try {
                Admin newAdmin = service.createAdmin(admin);
                return new ResponseEntity<Admin>(newAdmin, new HttpHeaders(), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<Admin>(new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<Admin>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping
    public ResponseEntity<Admin> UpdateAdmin(@RequestBody Admin admin)
            throws RecordNotFoundException {
        if (admin != null) {
            try {
                Admin newAdmin = service.createAdmin(admin);
                return new ResponseEntity<Admin>(newAdmin, new HttpHeaders(), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<Admin>(new HttpHeaders(), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<Admin>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteAdminById(@PathVariable("id") String id)
            throws RecordNotFoundException {
        if (id != null) {
            try {
                service.deleteAdmin(id);
                return HttpStatus.ACCEPTED;
            } catch (Exception e) {
                return HttpStatus.NOT_FOUND;
            }
        } else {
            return HttpStatus.BAD_REQUEST;
        }

    }

}
