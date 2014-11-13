package com.dariksoft.bazaar.domain;

import java.util.Date;

public class Product extends Item {

	private Date expireDate;
	
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

}
