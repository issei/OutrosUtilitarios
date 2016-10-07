package br.com.issei.estatistica.exception;

public class CalculoException extends Exception {

	private static final long serialVersionUID = 673872676359512261L;

	public CalculoException(String string) {
		super(string);
	}

	public CalculoException(NumberFormatException e) {
		super(e);
	}

}
