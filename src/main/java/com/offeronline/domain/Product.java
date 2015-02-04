package com.offeronline.domain;

import java.util.Date;

import javax.persistence.Entity;


@Entity
public class Product extends Deal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date expireDate;
	
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

}
