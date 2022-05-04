package com.app.ApiRestFul.services;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.app.ApiRestFul.exceptions.RecordNotFoundException;
import com.app.ApiRestFul.model.Content;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class WhatsAppService {
	 public static final String ACCOUNT_SID =System.getenv("TWILIO_ACCOUNT_SID");
	 public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
	
	private static final Logger log4 = LoggerFactory.getLogger(WhatsAppService.class);
	
	/**
	 * 
	 * @param c (Content)
	 * @return message (URL de la imagen (Felicitacion), Cuerpo del Mensaje, Destinatario del mensaje (Client))
	 * @throws NullPointerException
	 */
	public Message sendMessage(Content c) throws NullPointerException {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message message = null;
		
		try {
			 message = Message.creator(
		                new PhoneNumber("whatsapp:"+c.getClient().getPhone()), //to
		                new PhoneNumber("whatsapp:+14155238886"), //from
		                c.getMessage()).setMediaUrl(URI.create(c.getFelicitation().getImage()))
		            .create();
			 log4.info("EL MENSAJE HA SIDO ENVIADO"+ message.getSid());
		} catch (NullPointerException e) {
			log4.error("EL MENSAJE NO HA PODIDO SER ENVIADO "+e);
			throw new RecordNotFoundException("ERROR EN EL ENVIO");
		}
	       return message;
	}
}
