package com.rufino.simplepicpay.exception;

public class TransactionException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public TransactionException(String message) {
		super(message);
	}

}
