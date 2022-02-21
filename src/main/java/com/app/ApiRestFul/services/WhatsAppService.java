package com.app.ApiRestFul.services;

import java.net.URI;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.app.ApiRestFul.exceptions.RecordNotFoundException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class WhatsAppService {
	 public static final String ACCOUNT_SID =System.getenv("TWILIO_ACCOUNT_SID");
	 public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
	
	private static final Logger log4 = LoggerFactory.getLogger(WhatsAppService.class);
	
	/**
	 * @param kid
	 * @return Crea un niño con los parametros pasados en la BBDD
	 * @throws RecordNotFoundException,NullPointerException,IllegalArgumentException
	 */
	public Message sendMessage() throws RecordNotFoundException {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		 Message message = null;
		try {
			 message = Message.creator(
		                new PhoneNumber("whatsapp:+34697970637"), //to
		                new PhoneNumber("whatsapp:+14155238886"), //from
		                "Hola a todos")
		            .create();
			 log4.info("EL MENSAJE HA SIDO ENVIADO"+ message.getSid());
		} catch (Exception e) {
			log4.error("EL MENSAJE NO HA PODIDO SER ENVIADO"+message.getErrorCode());
			throw new RecordNotFoundException("ERROR EN EL ENVIO");
		}
	       return message;
	}
}
