package com.dariksoft.bazaar.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class  Item {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	@NotEmpty
	private String name;
	private double price;
	private ItemStatus status;
	private Rate rate;
	@ManyToOne
	@JoinColumn(name="cat_id", nullable=false)
	private ItemCategory category;
	
	@OneToMany(targetEntity = Deal.class, mappedBy = "item")
	private Set<Deal> deals;
	
	@ManyToOne
	@JoinColumn(name="merchant_id", nullable=false)
	private Merchant merchant;
	 @Column( length = 10000000)
	private byte[] thumbnail;
	
	@OneToMany(targetEntity = Attachment.class, fetch= FetchType.EAGER)
	private Set<Attachment> images;
	private String[] tags;
	private String description;
	private String details;
	private String finePrint;

	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public Set<Deal> getDeals() {
		return deals;
	}
	public void setDeals(Set<Deal> promotions) {
		this.deals = promotions;
	}
	public ItemCategory getCategory() {
		return category;
	}
	public void setCategory(ItemCategory category) {
		this.category = category;
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

	public byte[] getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}
	public Set<Attachment> getImages() {
		return images;
	}
	public void setImages(Set<Attachment> images) {
		this.images = images;
	}
	public Rate getRate() {
		return rate;
	}
	public void setRate(Rate rate) {
		this.rate = rate;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getFinePrint() {
		return finePrint;
	}
	public void setFinePrint(String finePrint) {
		this.finePrint = finePrint;
	}

}
