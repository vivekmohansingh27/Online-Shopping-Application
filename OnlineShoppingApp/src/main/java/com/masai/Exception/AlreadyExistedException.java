package com.masai.Exception;

public class AlreadyExistedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyExistedException() {
		super();
	}

	public AlreadyExistedException(String message) {
		super(message);
	}

}
