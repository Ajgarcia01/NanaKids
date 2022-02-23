package com.app.ApiRestFul.services;


import com.app.ApiRestFul.exceptions.RecordNotFoundException;

import com.app.ApiRestFul.model.Admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ApiRestFul.repository.AdminRepository;
import java.util.List;
import java.util.Optional;

/*
 * @author=Eduardo Carmona Lopez
 */

@Service
public class AdminService {

	@Autowired
	AdminRepository repository;

	private static final Logger log4 = LoggerFactory.getLogger(AdminService.class);

	/**
	 * Devuelve una lista de todos los Administradores de la base de datos
	 * 
	 * @return Lista de todos los administradores
	 * 
	 *         En caso de error nos devolveria un NullPointerException, ya que no
	 *         hay nada, la busqueda sera nula
	 */
	public List<Admin> getAllAdmin() throws RecordNotFoundException {
		List<Admin> admin = repository.findAll();
		if (!admin.isEmpty()) {
			log4.info("Se ha obtenido todos los administradores");
			return admin;
		} else {
			log4.info("ERROR:No se ha encontrado administradores");
			throw new RecordNotFoundException("No hay administradores", "No hay administradores");
		}

	}

	/**
	 * 
	 * @param id Identidicador de administrador
	 * @return Devuelve un administrador de la base de datos por un ID
	 * 
	 *         En caso de error nos devolveria un NullPointerException, ya que no
	 *         hay nada, la busqueda sera nula
	 */
	public Admin getAdminById(Long id)
			throws RecordNotFoundException, IllegalArgumentException, NullPointerException {
		if (id != null) {
			try {
				Optional<Admin> client = repository.findById(id);

				if (client.isPresent()) {
					log4.info("Se ha obtenido el administrador por ID");
					return client.get();
				} else {
					log4.error("ERROR:No se ha obtenido Administrador por ID");
					throw new RecordNotFoundException("No client record exist for given id", id);
				}
			} catch (IllegalArgumentException e) {
				log4.error("ERROR:ID introducido de administrador incorrecto");
				throw new IllegalArgumentException(e);
			}
		} else {
			log4.error("ERROR:Hay datos nulo al obtener el administrador por ID");
			throw new NullPointerException("Valor nulo");
		}

	}

	/**
	 * 
	 * @param admin Administrador que vamos a anadir a la base de datos
	 * @return Devuelve el administrador
	 * 
	 *         En caso de error nos devolveria un NullPointerException, ya que no
	 *         hay nada, la busqueda sera nula
	 */
	public Admin createAdmin(Admin admin)
			throws RecordNotFoundException, NullPointerException, IllegalArgumentException {
		if (admin != null) {
			try {
				admin = repository.save(admin);
				log4.info("Creado administrador correctamente");
				return admin;
			} catch (IllegalArgumentException e) {
				log4.error("ERROR:Valores mal introducidos al crear el administrador");
				throw new IllegalArgumentException(e);
			}
		} else {
			log4.error("ERROR:Valores nulos al crear el administrador");
			throw new NullPointerException("Valor nulo");
		}
	}

	/**
	 * Actualiza un administrador de la base de datos
	 * 
	 * @param entity administrador que vamos a actualizar
	 * @return Administrador actualizado
	 * 
	 *         En caso de error nos devolveria un NullPointerException, ya que no
	 *         hay nada, la busqueda sera nula
	 */
	public Admin UpdateAdmin(Admin entity)
			throws RecordNotFoundException, IllegalArgumentException, NullPointerException {
		if (entity.getUser() != null) {
			try {
				Optional<Admin> admin = repository.findById(entity.getId());

				if (admin.isPresent()) {
					Admin newAdmin = admin.get();
					newAdmin.setEmail(entity.getEmail());
					newAdmin.setPassword(entity.getPassword());
					newAdmin = repository.save(newAdmin);
					log4.info("Administrador actualizazdo");
					return newAdmin;
					

				} else {
					log4.error("ERROR:Se ha recibido datos incorrectos de administrador");
					throw new RecordNotFoundException("Client not found", entity.getUser());
				}

			} catch (IllegalArgumentException e) {
				log4.error("ERROR:Valores mal introducidos al actualizar el administrador");
				throw new IllegalArgumentException(e);
			}

		} else {
			log4.error("ERROR:Hay datos nulos al actualizar el administrador");
			throw new NullPointerException("Valor Nulo");
		}

	}

	/**
	 * Elimina un administrador de la base de datos por un ID
	 * 
	 * @param id Identificador del administrador
	 * 
	 *           En caso de error nos devolveria un NullPointerException, ya que no
	 *           hay nada, la busqueda sera nula
	 */
	public void deleteAdmin(Long id) throws RecordNotFoundException, IllegalArgumentException, NullPointerException {

		if (id != null) {
			try {
				Optional<Admin> Admin = repository.findById(id);
				if (Admin.isPresent()) {
					repository.deleteById(id);
					log4.info("Se ha eliminado correctamente el administrador");
				} else {
					log4.error("ERROR:No se ha eliminado el  Administrador por ID");
					throw new RecordNotFoundException("No client record exist for given id", id);
				}
			} catch (IllegalArgumentException e) {
				log4.error("ERROR:Valores mal introducidos al eliminar el administradror");
				throw new IllegalArgumentException(e);
			}
		} else {
			log4.error("ERROR:Valores nulos al eliminar el administrador");
			throw new NullPointerException("Valor nulo");
		}

	}
}
