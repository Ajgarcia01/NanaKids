package com.app.ApiRestFul.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.app.ApiRestFul.model.Content;
import com.app.ApiRestFul.services.WhatsAppService;
import com.twilio.rest.api.v2010.account.Message;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/message")
public class WhatsAppController {
	@Autowired
	WhatsAppService service;
	
	/**
	 * @return EndPoint que nos devuelve un HttpStatus.OK y todos los ninos que
	 *         existan en la BBDD a traves del servicio
	 */
    @ApiOperation(value = "Send Message on Api WhatsApp by Twilio", notes = "Returns a send message on whatsapp")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully retrieved"),
      @ApiResponse(code = 404, message = "Not found"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 500, message = "Internal Error ")
    })
    @CrossOrigin(methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
	@PostMapping
	public ResponseEntity<Message> sendMessage(@RequestBody Content c) throws ResponseStatusException {
		if (c!=null) {
			try {
				Message message = service.sendMessage(c);
				return new ResponseEntity<Message>(message,new HttpHeaders(), HttpStatus.OK);
			} catch (ResponseStatusException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El mensaje no ha sido enviado correctamente", e);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La peticion no se ha realizado correctamente");
		}
	}
}
