package com.app.ApiRestFul.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ApiRestFul.model.Client;
import com.app.ApiRestFul.model.Kid;
import com.app.ApiRestFul.repository.KidRepository;
import com.app.ApiRestFul.exceptions.RecordNotFoundException;

@Service
public class KidService {
	@Autowired
	KidRepository repository;
	
	/*
	 * 
	 * 
	 */
	
	public List<Kid> getAllKids() {
		List<Kid> result = repository.findAll();
		return result;
	}
	
	
	/*
	 * 
	 * 
	 */
	
	public Kid createClient(Kid kid) {
        kid = repository.save(kid);
        return kid;
    }
	
	
	/*
	 * 
	 * 
	 */
	
	public Kid Update(Kid kid) {
		if (kid.getId() != null && kid.getId() > 0) {
			Optional<Kid> n = repository.findById(kid.getId());
			if (n.isPresent()) { // UPDATE
				Kid newKid = n.get();
				newKid.setId(kid.getId());
				newKid.setBirthDate(kid.getBirthDate());
				newKid.setGender(kid.isGender());
				newKid.setName(kid.getName());
				newKid = repository.save(newKid);
				return newKid;
			} else { // INSERT
				kid.setId(null);
				kid = repository.save(kid);
				return kid;
			}
		} else {
			kid = repository.save(kid);
			return kid;
		}
	}
	
	
	/*
	 * 
	 * 
	 */
	
	
	public void deleteKidById(Long id)
	{
		Optional<Kid> kid = repository.findById(id);

		if (kid.isPresent()) {
			repository.deleteById(id);
		} else {
			 throw new RecordNotFoundException("Nota no existe", id);
		}

	}
	
	/*
	 * 
	 * 
	 */

	public Kid getNotesById(Long id) {
		Optional<Kid> result = repository.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new RecordNotFoundException("El niño no existe", id);
		}

	}
	
	
	public List<Client> getClientsByKid(Long id){
		List<Client> result= repository.getClients(id);
		return result;
	}
	
	
	public List<Kid> getKidByName(String title) {
		List<Kid> list = repository.getByName(title);
		
		if(list.size() > 0) {
			return list;
		}else {
			return new ArrayList<Kid>();
		}
	}
	
}