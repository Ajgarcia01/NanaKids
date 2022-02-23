package com.app.ApiRestFul.controller;

import com.app.ApiRestFul.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.app.ApiRestFul.services.AdminService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

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

/*
 * @author=Eduardo Carmona Lopez
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService service;
    //antes de tocar esto tener el servicio hecho

	/**
	 * @return EndPoint que nos devuelve un HttpStatus.OK y todos los administradores que
	 *         existan en la BBDD a traves del servicio
	 */
    @ApiOperation(value = "Get all admin", notes = "Returns an admin list")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping  
    public ResponseEntity<List<Admin>> getAllClient() {
        List<Admin> list = service.getAllAdmin();
        return new ResponseEntity<List<Admin>>(list, new HttpHeaders(), HttpStatus.OK);
    }

	/**
	 * @param id
	 * @return administrador en concreto de la BBDD con el id pasado por
	 *         parametro
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en funcion de la
	 *                                 peticion)
	 */
    @ApiOperation(value = "Get a admin by id", notes = "Returns a admin as per the id")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,Admin found"),
      @ApiResponse(code = 404, message = "ERROR:Admin not found"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable("id") Long id)
    		 throws ResponseStatusException {
        if (id != null) {
            try {
                Admin admin = service.getAdminById(id);
                return new ResponseEntity<Admin>(admin, new HttpHeaders(), HttpStatus.OK);
            }catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El administrador con id: " + id + "no se ha encontrado",
						e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}

    }

	/**
	 * @param admin
	 * @return EndPoint que nos devuelve un HttpStatus.OK y un nuevo administrador creado en
	 *         la BBDD
	 * @throws ResponseStatusException ( En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en funcion de la
	 *                                 peticion)
	 */
    @ApiOperation(value = "Create a admin", notes = "Returns a new  admin")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved,Admin created"),
      @ApiResponse(code = 404, message = "ERROR:Admin can't be create"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) throws ResponseStatusException {
        if (admin != null) {
            try {
                Admin newAdmin = service.createAdmin(admin);
                return new ResponseEntity<Admin>(newAdmin, new HttpHeaders(), HttpStatus.OK);
            } catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El administrador no ha sido guardado correctamente", e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}

    }
    
	/**
	 * @param admin
	 * @return EndPoint que nos devuelve un HttpStatus.OK y un administrador actualizado en
	 *         la BBDD
	 * @throws ResponseStatusException (nos devolveria unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en funcion de la
	 *                                 peticion)
	 */
    @ApiOperation(value = "Update a admin", notes = "Returns a admin updated")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved, Admin updated"),
      @ApiResponse(code = 404, message = "ERROR:Admin can't be update"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
    @PutMapping
    public ResponseEntity<Admin> UpdateAdmin(@RequestBody Admin admin)
    		 throws ResponseStatusException {
        if (admin != null) {
            try {
                Admin newAdmin = service.UpdateAdmin(admin);
                return new ResponseEntity<Admin>(newAdmin, new HttpHeaders(), HttpStatus.OK);
            } catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El administrador no ha sido actualizado correctamente",
						e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}

    }

	/**
	 * @param id
	 * @return EndPoint que nos devuelve un HttpStatus.OK y un administrador eliminado en la
	 *         BBDD
	 * @throws ResponseStatusException (En caso de error nos devolveria
	 *                                 unHttpStatus.NOT_FOUND o
	 *                                 HttpStatus.BAD_REQUEST, en funcion de la
	 *                                 peticion)
	 */
    @ApiOperation(value = "Delete a admin", notes = "Returns a admin deleted")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved, Admin deleted"),
      @ApiResponse(code = 404, message = "ERROR:Admin can't be delete"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
    @DeleteMapping("/{id}")
    public HttpStatus deleteAdminById(@PathVariable("id") Long id)
    		 throws ResponseStatusException {
        if (id != null) {
            try {
                service.deleteAdmin(id);
                return HttpStatus.ACCEPTED;
            }catch (ResponseStatusException e) {

				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El administrador no ha sido eliminado correctamente",
						e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}

    }

}
