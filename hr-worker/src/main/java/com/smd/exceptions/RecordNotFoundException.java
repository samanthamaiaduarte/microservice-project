package com.smd.exceptions;

public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public RecordNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public RecordNotFoundException(String mensagem, Object ... params) {
		super(String.format(mensagem, params));
	}
}
