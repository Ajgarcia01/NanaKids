package com.app.ApiRestFul.services;

import com.app.ApiRestFul.exceptions.RecordNotFoundException;
import com.app.ApiRestFul.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.ApiRestFul.repository.AdminRepository;
import java.util.List;
import java.util.Optional;

public class AdminService {
	@Autowired
	AdminRepository repository;
        
        
    public List<Admin> getAllAdmin() {
        List<Admin> admin = repository.findAll();
        return admin;
    }

    public Admin getAdminById(String id) throws RecordNotFoundException {
        Optional<Admin> client = repository.findById(id);

        if (client.isPresent()) {
            return client.get();
        } else {
            throw new RecordNotFoundException("No client record exist for given id", id);
        }
    }

    public Admin createAdmin(Admin admin) {
        admin = repository.save(admin);
        return admin;
    }

    public Admin UpdateAdmin(Admin entity) {
        if (entity.getUser() != null) {
            Optional<Admin> admin = repository.findById(entity.getUser());

            if (admin.isPresent()) {
                Admin newAdmin = admin.get();
                newAdmin.setEmail(entity.getEmail());
                newAdmin.setPassword(entity.getPassword());
 
                return newAdmin;
            } else {
                throw new RecordNotFoundException("Client not found",entity.getUser());
            }
        } else {
            throw new RecordNotFoundException("Client not found",entity.getUser());
        }

    }

    public void deleteAdmin(String id) throws RecordNotFoundException {
        Optional<Admin> Admin = repository.findById(id);
        if (Admin.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No client record exist for given id", id);
        }
    }
}
