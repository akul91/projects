package com.practice.tms.exception;


public class TMSException extends Exception{

	private static final long serialVersionUID = 1L;
	String message;
	public TMSException(String message)
	{
		this.message=message;
	}

	@Override
	public String toString() {

		return message;
	}
}
