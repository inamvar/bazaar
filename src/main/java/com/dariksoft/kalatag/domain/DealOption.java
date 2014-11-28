package com.dariksoft.kalatag.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class DealOption  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String name;
	private double discount;
	private String description;
	@Transient
	private int remove;
	
	@ManyToOne
	@JoinColumn(name="deal_id", nullable=false)
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getRemove() {
		return remove;
	}


	public void setRemove(int remove) {
		this.remove = remove;
	}
	

}
