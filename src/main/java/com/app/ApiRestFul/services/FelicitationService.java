package com.app.ApiRestFul.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.ApiRestFul.exceptions.RecordNotFoundException;
import com.app.ApiRestFul.model.Felicitation;
import com.app.ApiRestFul.repository.FelicitationRepository;

public class FelicitationService {
	@Autowired
	FelicitationRepository repository;

	// OBTENER TODAS LAS FELICITACIOS
	public List<Felicitation> getAllFelicitations() {
		List<Felicitation> result = repository.findAll();
		return result;
	}

	// OBTENER FELICITACIONES POR ID
	public Felicitation getFelicitationById(Long id) throws RecordNotFoundException {
		Optional<Felicitation> result = repository.findById(id);
		if (result.isPresent()) {
			return result.get();

		} else {
			throw new RecordNotFoundException("", id);
		}

	}

	// CREAR O ACTUALIZAR FELICITACION
	public Felicitation createOrUpdate(Felicitation felicitation) {
		if (felicitation.getId() != null && felicitation.getId() > 0) {
			Optional<Felicitation> f = repository.findById(felicitation.getId());

			if (f.isPresent()) {

				// Actualizar

				Felicitation newFelicitation = f.get();
				newFelicitation.setId(felicitation.getId());
				newFelicitation.setImage(felicitation.getImage());
				return newFelicitation;
			} else {
				felicitation.setId(null);
				felicitation = repository.save(felicitation);
				return felicitation;
			}
		} else {
			felicitation = repository.save(felicitation);
			return felicitation;
		}
	}

	// BORRAR FELICITACION POR ID
	public void deleteFelicitation(Long id) {

		Optional<Felicitation> result = repository.findById(id);
		if (result.isPresent()) {
			repository.deleteById(id);
		} else {
			// NOTA BORRADA POR ID
		}
	}
	/*
	 * public int numberFelicitation() { List<Felicitation> result =
	 * repository.findAll(); int number = result.size(); return number;
	 * 
	 * }
	 */
	// OBTENER NUMERO DE FELICITACIONES

	public Long numberFelicitations() {
		Long number = repository.count();
		return number;

	}
	// CAMBIAR ESTADO DE FELICITACION
	// DEVUELVE TRUE SI TODAS CAMBIAN SU ESTADO A ENVIADO
	/*
	 * public boolean changeStatusFelicitation(List<Felicitation> listFelicitation)
	 * {
	 * 
	 * Felicitation x; boolean result = false;
	 * 
	 * if (listFelicitation != null && listFelicitation.size() > 0) { for(int
	 * i=0;i<listFelicitation.size();i++) {
	 * 
	 * x = listFelicitation.get(i); if(!x.isEstate()) { x.setEstate(true); }
	 * 
	 * } result = true;
	 * 
	 * } return result; }
	 */
	/*
	 * //????DUDA????? public List<Felicitation>
	 * changeStatusFelicitation(List<Felicitation> listFelicitation) {
	 * 
	 * 
	 * Felicitation x; boolean status = true;
	 * 
	 * if (listFelicitation != null && listFelicitation.size() > 0) {
	 * 
	 * for(int i=0;i<listFelicitation.size();i++) { x = listFelicitation.get(i);
	 * if(!x.isEstate()) { x = repository.updateStatus(status, x.getId()); } }
	 * 
	 * } List<Felicitation> ls = repository.findAll(); return ls;
	 * 
	 * }
	 */

	//DEVUELVE UN HTTPSTATUS
	public void changeStatusFelicitation(List<Felicitation> listFelicitation) {
		Felicitation x;
		boolean status = true;
		if (listFelicitation != null && listFelicitation.size() > 0) {

			for (int i = 0; i < listFelicitation.size(); i++) {
				x = listFelicitation.get(i);
				if (!x.isEstate()) {
					x = repository.updateStatus(status, x.getId());
				}
			}

		}
	}

	public List<Felicitation> listTypeFelicitation(int TypeFelicitation) {
		List<Felicitation> listFelicitation = repository.getByType(TypeFelicitation);
		return listFelicitation;
	}

}
