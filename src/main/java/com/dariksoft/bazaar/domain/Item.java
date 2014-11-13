package com.dariksoft.bazaar.domain;

import java.util.List;

public class  Item {
	
	private int id;
	private String name;
	private double price;
	private ItemStatus status;
	private ItemCategory Category;
	private List<Promotion> promotions;
	private Merchant merchant;
	
	
	public int getId() {
		return id;
	}
	public List<Promotion> getPromotions() {
		return promotions;
	}
	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}
	public ItemCategory getCategory() {
		return Category;
	}
	public void setCategory(ItemCategory category) {
		Category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	public ItemStatus getStatus() {
		return status;
	}
	public void setStatus(ItemStatus status) {
		this.status = status;
	}

}
