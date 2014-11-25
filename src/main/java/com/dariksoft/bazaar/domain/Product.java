package com.dariksoft.bazaar.domain;

import java.util.Date;

import javax.persistence.Entity;


@Entity
public class Product extends Deal {

	private Date expireDate;
	
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

}
