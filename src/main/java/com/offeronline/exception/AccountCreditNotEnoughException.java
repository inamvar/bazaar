package com.offeronline.exception;

public class AccountCreditNotEnoughException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message = "Sorry! This account has't enough credit.";
	
	public AccountCreditNotEnoughException(){
		super();
	}
	
	public AccountCreditNotEnoughException( String message){
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
