package com.dariksoft.bazaar.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DealOption {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private double discount;
	private String description;
	
	@ManyToOne
	@JoinColumn(name="deal_id", nullable=true)
	private Deal deal;
	
	public Deal getDeal() {
		return deal;
	}


	public void setDeal(Deal deal) {
		this.deal = deal;
	}


	public double getDiscount() {
		return discount;
	}


	public void setDiscount(double discount) {
		this.discount = discount;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	

}
