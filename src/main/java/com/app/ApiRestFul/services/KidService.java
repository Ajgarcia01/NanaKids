package com.app.ApiRestFul.services;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ApiRestFul.model.Kid;
import com.app.ApiRestFul.repository.KidRepository;
import com.app.ApiRestFul.exceptions.RecordNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * @author=Jesús García Luque 
 */

@Service
public class KidService {
	@Autowired
	KidRepository repository;

	private static final Logger log4 = LoggerFactory.getLogger(KidService.class);

	/*
	 * 
	 * Devuelve una lista de todos los niños que se encuentran en la BBDD
	 * 
	 * En caso de error nos devolveria un NullPointerException, ya que no hay nada,
	 * la búsqueda sería nula
	 * 
	 */

	public List<Kid> getAllKids() throws RecordNotFoundException {
		List<Kid> result = repository.findAll();
		if (!result.isEmpty()) {
			log4.info("Se han obtenido todos los niños con éxito");
			return result;
		} else {
			log4.error("ERROR: No se han encontrado niños en la base de datos");
			throw new RecordNotFoundException("No hay valores");
		}

	}

	/*
	 * 
	 * Recibe un niño, el cual con la llamada al repositorio se guarda en la base de
	 * datos si no actualiza uno que ya existe
	 * 
	 * En caso de error nos devolveria un IllegalArgumentException, si falta algun
	 * valor o hay error. Si el valor es nulo tendriamos un NullPointerException
	 * 
	 */

	public Kid createKid(Kid kid) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if (kid != null) {
			if (kid != null && kid.getId() < 0) {
				try {
					kid = repository.save(kid);
					log4.info("Niño creado con éxito");
					return kid;
				} catch (IllegalArgumentException e) {
					log4.error("Se han recibido datos incorrectos al crear niño, ERROR: " + e);
					throw new IllegalArgumentException(
							"Los valores introducidos no son correctos" + "IllegalArgumentException: " + e);
				}
			} else {
				return Update(kid);
			}

		} else {
			log4.error("ERROR: Hay datos que son nulos al crear niño");
			throw new NullPointerException("Valor nulo");
		}

	}

	/*
	 * 
	 * Actualiza un niño de la base de datos y si no existe lo crea (pasando un niño
	 * por parametro)
	 * 
	 * En caso de error nos devolveria un IllegalArgumentException, si falta algun
	 * valor o hay error. Si el valor es nulo tendriamos un NullPointerException
	 * 
	 */

	public Kid Update(Kid kid) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if (kid != null) {
			try {
				Optional<Kid> n = repository.findById(kid.getId());// si hay algo lo busca por id en la bbdd
				if (n.isPresent()) { // UPDATE
					Kid newKid = n.get(); // se trae el niño que hemos metido para setear los campos
					newKid.setId(kid.getId());
					newKid.setBirthDate(kid.getBirthDate());
					newKid.setGender(kid.isGender());
					newKid.setName(kid.getName());
					newKid.setClient(kid.getClient());
					newKid.setFelicitations(kid.getFelicitations());
					newKid = repository.save(newKid);
					log4.info("INFO: El niño con nombre: "+kid.getName()+", ha sido actualizado al nombre: "+newKid.getName());
					return newKid;

				} else {
					log4.error("ERROR: Se han recibido datos incorrectos al crear niño");
					throw new IllegalArgumentException("Los valores introducidos no son correctos");
				}
			} catch (Exception e) {
				log4.error("ERROR: valores mal introducidos al actulizar un niño");
				throw new RecordNotFoundException("Los valores introducidos no son correctos");
			}

		} else {
			log4.error("ERROR: Hay datos que son nulos al crear niño");
			throw new NullPointerException("Valor nulo");
		}
	}

	/*
	 * 
	 * Elimina un niño de la base de datos (recibiendo el id del niño por parámetro)
	 * 
	 * En caso de error nos devolveria un NullPointerException si el valor es nulo y
	 * un RecordNotFoundException si el valor es inválido
	 * 
	 */

	public void deleteKidById(Long id) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if (id != null && id > -1) {
			try {
				Optional<Kid> kid = repository.findById(id);
				if (kid.isPresent()) {
					log4.info("INFO: Niño eliminado con id: " + id);
					repository.deleteById(id);

				}
			} catch (Exception e) {
				log4.error("ERROR: Valores nulos introducidos");
				throw new IllegalArgumentException(
						"Los valores introducidos no son correctos" + "IllegalArgumentException: " + e);
			}
		} else {
			log4.error("ERROR: Valores nulos introducidos");
			throw new NullPointerException();
		}
	}

	/*
	 * 
	 * Devuelve un niño (Concretamente el niño que tenga la id que es pasada por
	 * parametro)
	 * 
	 * En caso de error nos devolveria un IllegalArgumentException, si falta algun
	 * valor o hay error. Si el valor es nulo tendriamos un NullPointerException
	 * 
	 */

	public Kid getKidById(Long id) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if (id != null) {
			try {
				Optional<Kid> result = repository.findById(id);
				if (result.isPresent()) {
					log4.info("INFO: Niño con id " + id + " ha sido encontrado en la base de datos");
					return result.get();
				} else {
					log4.info("ERROR: No hay resultados en obtener un niño para el id: " + id);
					throw new RecordNotFoundException("No se han encontrado valores para el id: ", id);
				}
			} catch (IllegalArgumentException e) {
				log4.info("ERROR: Los datos introducidos para encontrar un niño por id no son correctos");
				throw new IllegalArgumentException(
						"Los valores introducidos no son correctos" + "IllegalArgumentException: " + e);
			}
		} else {
			log4.info("ERROR: Los datos introducidos para encontrar un niño por id son nulos");
			throw new NullPointerException("Valor nulo");
		}
	}

	/*
	 * 
	 * Devuelve una lista de todos los niños que se encuentran en la BBDD con el
	 * nombre pasado por parámetro
	 * 
	 * En caso de error nos devolveria un IllegalArgumentException, si falta algun
	 * valor o hay error. Si el valor es nulo tendriamos un NullPointerException
	 * 
	 */

	public List<Kid> getKidByName(String title)
			throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if (title != null) {
			try {
				List<Kid> list = repository.getByName(title);

				if (list.size() > 0) {
					log4.info("INFO: La búsqueda de niños encontrados por nombre ha sido realizada");
					return list;
				} else {
					log4.info("ERROR: No hay resultados en obtener un niño para el nombre: " + title);
					throw new RecordNotFoundException("No hay resultados", title);
				}

			} catch (IllegalArgumentException e) {
				log4.info("ERROR: Los datos introducidos para encontrar un niño por nombre no son correctos");
				throw new IllegalArgumentException(
						"Los valores introducidos no son correctos" + "IllegalArgumentException: " + e);
			}
		} else {
			log4.info("ERROR: Los datos introducidos para encontrar un niño por nombre son nulos");
			throw new NullPointerException();
		}
	}

}