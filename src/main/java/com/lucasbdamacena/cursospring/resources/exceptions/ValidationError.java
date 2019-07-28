package com.lucasbdamacena.cursospring.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	
	private static final long serialVersionUID = 1L;
	
	List<FieldMessage> errors = new ArrayList<FieldMessage>();
	
	public ValidationError(Integer code, String msg, long timeStamp) {
		super(code, msg, timeStamp);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message ) {		
		this.errors.add(new FieldMessage(fieldName, message));
	}

}
