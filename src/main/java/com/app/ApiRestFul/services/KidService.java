package com.app.ApiRestFul.services;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ApiRestFul.model.Kid;
import com.app.ApiRestFul.repository.KidRepository;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.app.ApiRestFul.exceptions.RecordNotFoundException;

/*
 * @author=Jesús García Luque 
 */

@Service
public class KidService {
	@Autowired
	KidRepository repository;
	
	/*
	 * 
	 * Devuelve una lista de todos los niños que se encuentran en la BBDD
	 * 
	 * En caso de error nos devolveria un NullPointerException, ya que no hay nada, la búsqueda sería nula
	 * 
	 */
	
	public List<Kid> getAllKids() throws NullPointerException {
		List<Kid> result = repository.findAll();
		if(!result.isEmpty()) {
			return result;
		}else {
			throw new NullPointerException("Valor nulo");
		}
		
	}
	
	/*
	 * 
	 * Recibe un niño, el cual con la llamada al repositorio se guarda en la base de datos
	 * 
	 * En caso de error nos devolveria un IllegalArgumentException, si falta algun valor o hay error. Si el valor es nulo tendriamos un NullPointerException
	 * 
	 */

	public Kid createClient(Kid kid) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
        if(kid!=null) {
        	try {
        		kid = repository.save(kid);
                return kid;
			} catch (Exception e) {
				throw new IllegalArgumentException();
			}
        }else {
        	throw new NullPointerException("Valor nulo");
        }
		
    }
	
	
	/*
	 * 
	 * Actualiza un niño de la base de datos y si no existe lo crea (pasando un niño por parametro)
	 * 
	 * En caso de error nos devolveria un IllegalArgumentException, si falta algun valor o hay error. Si el valor es nulo tendriamos un NullPointerException
	 * 
	 */
	
	public Kid Update(Kid kid) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if (kid.getId() != null && kid.getId() > 0) {
			Optional<Kid> n = repository.findById(kid.getId());
			if (n.isPresent()) { // UPDATE
				Kid newKid = n.get();
				newKid.setId(kid.getId());
				newKid.setBirthDate(kid.getBirthDate());
				newKid.setGender(kid.isGender());
				newKid.setName(kid.getName());
				newKid.setClient(kid.getClient());
				newKid.setFelicitations(kid.getFelicitations());
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
	 * Elimina un niño de la base de datos (recibiendo el id del niño por parámetro)
	 * 
	 * En caso de error nos devolveria un NullPointerException si el valor es nulo y un RecordNotFoundException si el valor es inválido
	 * 
	 */
	
	
	public void deleteKidById(Long id) throws RecordNotFoundException, NullPointerException {
		if(id!=null && id>-1) {
			try {
				Optional<Kid> kid = repository.findById(id);
				if (kid.isPresent()) {
					repository.deleteById(id);
				}
			} catch (Exception e) {
				throw new RecordNotFoundException("La id no es válida", id);
			}
		}else {
			throw new NullPointerException();
		}
	}
	
	/*
	 * 
	 * Devuelve un niño (Concretamente el niño que tenga la id que es pasada por parametro)
	 * 
	 * En caso de error nos devolveria un IllegalArgumentException, si falta algun valor o hay error. Si el valor es nulo tendriamos un NullPointerException
	 * 
	 */

	public Kid getKidById(Long id) throws RecordNotFoundException, NullPointerException, IllegalArgumentException, InvalidFormatException {
		if(id!=null) {
			try {
				Optional<Kid> result = repository.findById(id);
				if (result.isPresent()) {
					return result.get();
				} else {
					throw new RecordNotFoundException("El niño no existe", id);
				}
			} catch (IllegalArgumentException e) {
				throw new InvalidFormatException("Mete un Long", id, Kid.class);
			}
		}else {
			throw new NullPointerException("Valor nulo");
		}
	}
	
	
	/*
	 * 
	 * Devuelve una lista de todos los niños que se encuentran en la BBDD con el nombre pasado por parámetro
	 * 
	 * En caso de error nos devolveria un IllegalArgumentException, si falta algun valor o hay error. Si el valor es nulo tendriamos un NullPointerException
	 * 
	 */
	
	public List<Kid> getKidByName(String title) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if(title!=null) {
			try {
				List<Kid> list = repository.getByName(title);
				
				if(list.size() > 0) {
					return list;
				}else {
					throw new RecordNotFoundException("No hay resultados", title);
				}
			
			} catch (Exception e) {
				throw new IllegalArgumentException();
			}
		}else {
			throw new NullPointerException();
		}
	}
		
	
}