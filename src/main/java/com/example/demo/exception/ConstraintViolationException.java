package com.example.demo.exception;

public class ConstraintViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConstraintViolationException(String mensagem) {
		super(mensagem);
	}
}