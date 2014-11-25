package com.dariksoft.bazaar.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_item")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String description;
	private double quantity;
	private double price;
	
	@ManyToOne
	@JoinColumn(name="item_id", nullable=true)
	private Deal item;
	
	@ManyToOne
	@JoinColumn(name="coupon_id", nullable=true)
	private Coupon coupon;
	@ManyToOne
	@JoinColumn(name="order_id", nullable=false)
	private Order order;
	

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

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Deal getItem() {
		return item;
	}

	public void setItem(Deal item) {
		this.item = item;
	}

}
