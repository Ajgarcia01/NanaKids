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
 * @author=Jesus Garcia Luque 
 */

@Service
public class KidService {
	@Autowired
	KidRepository repository;

	private static final Logger log4 = LoggerFactory.getLogger(KidService.class);

	/**
	 * 
	 * @return Una lista de todos los ninos de la BBDD
	 * @throws RecordNotFoundException
	 */
	public List<Kid> getAllKids() throws RecordNotFoundException {
		List<Kid> result = repository.findAll();
		if (!result.isEmpty()) {
			log4.info("Se han obtenido todos los ni�os con �xito");
			return result;
		} else {
			log4.error("ERROR: No se han encontrado ni�os en la base de datos");
			throw new RecordNotFoundException("No hay valores");
		}

	}

	/**
	 * @param kid
	 * @return Crea un nino con los parametros pasados en la BBDD
	 * @throws RecordNotFoundException,NullPointerException,IllegalArgumentException
	 */
	public Kid createKid(Kid kid) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if (kid != null) {
				try {
					kid = repository.save(kid);
					log4.info("Nino creado con �xito");
					return kid;
				} catch (IllegalArgumentException e) {
					log4.error("Se han recibido datos incorrectos al crear ni�o, ERROR: " + e);
					throw new IllegalArgumentException(
							"Los valores introducidos no son correctos" + "IllegalArgumentException: " + e);
				}

		} else {
			log4.error("ERROR: Hay datos que son nulos al crear ni�o");
			throw new NullPointerException("Valor nulo");
		}

	}

	/**
	 * @param kid
	 * @return un nino que ya existe actualizado con nuevos valores
	 * @throws RecordNotFoundException, NullPointerException,
	 *                                  IllegalArgumentException
	 */
	public Kid Update(Kid kid) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if (kid != null) {
			try {
				Optional<Kid> n = repository.findById(kid.getId());// si hay algo lo busca por id en la bbdd
				if (n.isPresent()) { // UPDATE
					Kid newKid = n.get(); // se trae el nino que hemos metido para setear los campos
					newKid.setId(kid.getId());
					newKid.setBirthDate(kid.getBirthDate());
					newKid.setGender(kid.isGender());
					newKid.setName(kid.getName());
					newKid.setClient(kid.getClient());
					newKid.setFelicitations(kid.getFelicitations());
					newKid = repository.save(newKid);
					log4.info("INFO: El ni�o con nombre: " + kid.getName() + ", ha sido actualizado al nombre: "
							+ newKid.getName());
					return newKid;

				} else {
					log4.error("ERROR: Se han recibido datos incorrectos al crear nino");
					throw new IllegalArgumentException("Los valores introducidos no son correctos");
				}
			} catch (Exception e) {
				log4.error("ERROR: valores mal introducidos al actulizar un nino");
				throw new RecordNotFoundException("Los valores introducidos no son correctos");
			}

		} else {
			log4.error("ERROR: Hay datos que son nulos al crear nino");
			throw new NullPointerException("Valor nulo");
		}
	}

	/**
	 * @param id
	 * @return borra a un nino en concreto con el id pasado por parametro de la BBDD
	 * @throws RecordNotFoundException, IllegalArgumentException,
	 *                                  NullPointerException
	 */
	public void deleteKidById(Long id) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if (id != null && id > -1) {
			try {
				Optional<Kid> kid = repository.findById(id);
				if (kid.isPresent()) {
					log4.info("INFO: Nino eliminado con id: " + id);
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

	/**
	 * @param id
	 * @return un nino en concreto de la BBDD con el id pasado por parametro
	 * @throws RecordNotFoundException, NullPointerException,
	 *                                  IllegalArgumentException
	 */
	public Kid getKidById(Long id) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if (id != null) {
			try {
				Optional<Kid> result = repository.findById(id);
				if (result.isPresent()) {
					log4.info("INFO: Nino con id " + id + " ha sido encontrado en la base de datos");
					return result.get();
				} else {
					log4.info("ERROR: No hay resultados en obtener un nino para el id: " + id);
					throw new RecordNotFoundException("No se han encontrado valores para el id: ", id);
				}
			} catch (IllegalArgumentException e) {
				log4.info("ERROR: Los datos introducidos para encontrar un nino por id no son correctos");
				throw new IllegalArgumentException(
						"Los valores introducidos no son correctos" + "IllegalArgumentException: " + e);
			}
		} else {
			log4.info("ERROR: Los datos introducidos para encontrar un nino por id son nulos");
			throw new NullPointerException("Valor nulo");
		}
	}

	/**
	 * @param title
	 * @return un nino en concreto de la BBDD con el nombre pasado por parametro
	 * @throws RecordNotFoundException, NullPointerException,
	 *                                  IllegalArgumentException
	 */
	public List<Kid> getKidByName(String title)
			throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if (title != null) {
			try {
				List<Kid> list = repository.getByName(title);

				if (list.size() > 0) {
					log4.info("INFO: La busqueda de ninos encontrados por nombre ha sido realizada");
					return list;
				} else {
					log4.info("ERROR: No hay resultados en obtener un nino para el nombre: " + title);
					throw new RecordNotFoundException("No hay resultados", title);
				}

			} catch (IllegalArgumentException e) {
				log4.info("ERROR: Los datos introducidos para encontrar un nino por nombre no son correctos");
				throw new IllegalArgumentException(
						"Los valores introducidos no son correctos" + "IllegalArgumentException: " + e);
			}
		} else {
			log4.info("ERROR: Los datos introducidos para encontrar un nino por nombre son nulos");
			throw new NullPointerException();
		}
	}

}