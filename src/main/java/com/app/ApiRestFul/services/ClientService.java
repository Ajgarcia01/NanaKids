package com.app.ApiRestFul.services;

import com.app.ApiRestFul.exceptions.RecordNotFoundException;
import com.app.ApiRestFul.model.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ApiRestFul.repository.ClientRepository;
import java.util.List;
import java.util.Optional;

/*
 * @author=Eduardo Carmona Lopez
 */

@Service
public class ClientService {

	@Autowired
	ClientRepository repository;

	private static final Logger log4 = LoggerFactory.getLogger(ClientService.class);

	/*
	 * 
	 * Devuelve una lista de todos los clientes que se encuentran en la BBDD
	 * 
	 * En caso de error nos devolveria un NullPointerException, ya que no hay nada,
	 * la busqueda sera nula
	 * 
	 */
	public List<Client> getAllClient() throws NullPointerException {
		List<Client> clientes = repository.findAll();
		if (!clientes.isEmpty()) {
			log4.info("Se han obtenido todos los clientes con �xito");
			return clientes;
		} else {
			log4.error("ERROR: No se han encontrado clientes en la base de datos");
			throw new NullPointerException("Valor nulo");
		}

	}

	/*
	 * 
	 * Devuelve un cliente por un id de la BBDD
	 * 
	 * En caso de error nos devolveria un NullPointerException, ya que no hay nada,
	 * la busqueda seria nula
	 * 
	 */
	public Client getClientById(Long id)
			throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if (id != null) {
			try {
				Optional<Client> client = repository.findById(id);
				if (client.isPresent()) {
					log4.info("Se ha obtenido el cliente por su ID con exito");
					return client.get();
				} else {
					log4.error("No existe cliente con ese ID en la base de datos");
					throw new RecordNotFoundException("No client record exist for given id", id);
				}
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException(e);
			}
		} else {
			log4.error("ERROR: No se ha podido traer el cliente por su ID, Valor nulo");
			throw new NullPointerException("Valor nulo");
		}

	}

	/**
	 * Guardamos un cliente en la base de datos
	 * 
	 * @param cliente Recibe un cliente que es el que guarda en la BBDD
	 * @return devuelve el cliente que hemos anadido
	 * 
	 *         En caso de error nos devolveria un NullPointerException, ya que no
	 *         hay nada, la busqueda seria nula
	 */
	public Client createClient(Client cliente)
			throws NullPointerException, IllegalArgumentException, RecordNotFoundException {
		if (cliente != null) {
			try {
				cliente = repository.save(cliente);
				log4.info("Creado el cliente Correctamente");
				return cliente;
			} catch (IllegalArgumentException e) {
				log4.error("Se ha recibido valores incorrectos al crear el ni�o");
				throw new IllegalArgumentException(e);
			}
		} else {
			log4.error("ERROR: Hay datos nulos al crear el cliente");
			throw new NullPointerException("Valor nulo");
		}

	}

	/**
	 * Actualiza un cliente de la base de datos
	 * 
	 * @param entity Cliente que vamos a actualizar de la base de datos
	 * @return Devuelve el nuevo cliente
	 * 
	 *         En caso de error nos devolveria un NullPointerException, ya que no
	 *         hay nada, la busqueda seria nula
	 */
	public Client UpdateClient(Client entity) {
		if (entity.getId() != null) {
			Optional<Client> cliente = repository.findById(entity.getId());

			try {
				if (cliente.isPresent()) {
					Client newClient = cliente.get();
					newClient.setEmail(entity.getEmail());
					newClient.setName(entity.getName());
					newClient.setPhone(entity.getPhone());
					newClient.setSurname(entity.getSurname());
					newClient.setType(entity.isType());
					newClient.setKid(entity.getKid());
					newClient = repository.save(newClient);
					log4.info("Se ha actualizado el cliente correctamente");
					return newClient;
				} else {
					log4.error("ERROR:Se han recibido datos incorrectos al actualizar cliente");
					throw new IllegalArgumentException("Lo valores introducidos no son correctos ");

				}

			} catch (Exception e) {
				log4.error("ERROR:Valores mal introducidos en el cliente");
				throw new RecordNotFoundException("Client not found", entity.getId());
			}

		} else {
			log4.error("ERROR: Hay datos nulos al actualizar el cliemnte");
			throw new RecordNotFoundException("Client not found", entity.getId());
		}

	}

	/**
	 * Borra de la base de datos por el id de un cliente
	 * 
	 * @param id Identificador de cliente
	 * 
	 *           En caso de error nos devolveria un NullPointerException, ya que no
	 *           hay nada, la busqueda seriaa nula
	 * 
	 */
	public void deleteClient(Long id) throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if (id != null) {
			try {
				Optional<Client> cliente = repository.findById(id);
				if (cliente.isPresent()) {
					log4.info("Eliminador el cliente correctamente");
					repository.deleteById(id);
				} else {
					log4.error("ERROR:No existe cliente para eliminar con el ID");
					throw new RecordNotFoundException("No client record exist for given id", id);
				}
			} catch (IllegalArgumentException e) {
				log4.error("ERROR:Los datos introducidos para borrar el id no son correctos");
				throw new IllegalArgumentException(e);
			}

		} else {
			log4.error("ERROR:Los datos introducidos para borrar el id son nulos");
			throw new NullPointerException("Valor nulo");
		}

	}

}
