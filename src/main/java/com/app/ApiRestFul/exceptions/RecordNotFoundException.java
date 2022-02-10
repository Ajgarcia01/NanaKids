package com.app.ApiRestFul.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {
	private String ex;
	private Object f;

	public RecordNotFoundException(String ex, Long f) {
		super();
		this.ex = ex;
		this.f = f;
	}
	
	public RecordNotFoundException(String ex) {
		super();
		this.ex = ex;
	}
        
        public RecordNotFoundException(String ex, String f) {
		super();
		this.ex = ex;
		this.f = f;
	}


	public String getEx() {
		return ex;
	}

	public Object getF() {
		return f;
	}

}
