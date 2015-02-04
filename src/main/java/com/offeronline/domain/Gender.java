package com.offeronline.domain;


public enum Gender {
	MALE("gender.MALE"),
	Female("gender.FEMALE");
	
		
	private String messageCode;
	private Gender(String messageCode){
		this.messageCode = messageCode;
	}
	
	
    public String getMessageCode() {
    	return this.messageCode;
    }
}
