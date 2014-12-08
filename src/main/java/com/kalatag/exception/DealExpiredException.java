package com.kalatag.exception;

public class DealExpiredException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message = "Deal has been expired or it's not for sale any more.";
	
	public DealExpiredException(){
		super();
	}
	
	public DealExpiredException( String message){
		super();
		this.setMessage(message);
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
