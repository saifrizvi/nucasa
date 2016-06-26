package com.nookcasa.exceptions;

public class InvalidParametersException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidParametersException(String errorMessage) {
		super(errorMessage);
	}

}
