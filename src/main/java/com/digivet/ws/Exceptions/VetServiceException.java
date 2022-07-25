package com.digivet.ws.Exceptions;

public class VetServiceException extends RuntimeException {
	private static final long serialVersionUID = 1348771109171435607L;

	public VetServiceException(String message)
	{
		super(message);
	}
}
