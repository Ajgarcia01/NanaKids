package com.app.ApiRestFul.services;

import com.app.ApiRestFul.exceptions.RecordNotFoundException;
import com.app.ApiRestFul.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ApiRestFul.repository.AdminRepository;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    AdminRepository repository;

    public List<Admin> getAllAdmin() {
        List<Admin> admin = repository.findAll();
        return admin;
    }

    public Admin getAdminById(String id) throws RecordNotFoundException, IllegalArgumentException, NullPointerException {
        if (id != null) {
            try {
                Optional<Admin> client = repository.findById(id);

                if (client.isPresent()) {
                    return client.get();
                } else {
                    throw new RecordNotFoundException("No client record exist for given id", id);
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            throw new NullPointerException("Valor nulo");
        }

    }

    public Admin createAdmin(Admin admin) {
        admin = repository.save(admin);
        return admin;
    }

    public Admin UpdateAdmin(Admin entity) throws RecordNotFoundException, IllegalArgumentException, NullPointerException {
        if (entity.getUser() != null) {
            try {
                Optional<Admin> admin = repository.findById(entity.getUser());

                if (admin.isPresent()) {
                    Admin newAdmin = admin.get();
                    newAdmin.setEmail(entity.getEmail());
                    newAdmin.setPassword(entity.getPassword());

                    return newAdmin;
                } else {
                    throw new RecordNotFoundException("Client not found", entity.getUser());
                }

            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e);
            }

        } else {
            throw new NullPointerException("Valor Nulo");
        }

    }

    public void deleteAdmin(String id) throws RecordNotFoundException, IllegalArgumentException, NullPointerException {

        if (id != null) {
            try {
                Optional<Admin> Admin = repository.findById(id);
                if (Admin.isPresent()) {
                    repository.deleteById(id);
                } else {
                    throw new RecordNotFoundException("No client record exist for given id", id);
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            throw new NullPointerException("Valor nulo");
        }

    }
}
