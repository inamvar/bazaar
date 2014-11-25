package com.dariksoft.kalatag.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
public class  Deal {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	@NotEmpty
	private String name;
	private double price;
	private ItemStatus status;
	private Rate rate;
	private String features;
	private String termsOfUse;
	private String description;
	private int minCoupon;
	private int maxCoupon;
	private Date validity;
	


	@ManyToOne
	@JoinColumn(name="cat_id", nullable=false)
	private ItemCategory category;
		
	@ManyToOne
	@JoinColumn(name="merchant_id", nullable=false)
	private Merchant merchant;
	 @Column( length = 10000000)
	private byte[] thumbnail;
	
	@OneToMany(targetEntity = Attachment.class, fetch= FetchType.EAGER, cascade= CascadeType.ALL)
	private List<Attachment> images;
	private String[] tags;

	@OneToMany(targetEntity = DealOption.class,  cascade= CascadeType.ALL)
	private List<DealOption> options;

	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
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
	public List<Attachment> getImages() {
		return images;
	}
	public void setImages(List<Attachment> images) {
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

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getTermsOfUse() {
		return termsOfUse;
	}

	public void setTermsOfUse(String termsOfUse) {
		this.termsOfUse = termsOfUse;
	}

	public List<DealOption> getOptions() {
		return options;
	}

	public void setOptions(List<DealOption> options) {
		this.options = options;
	}

	public int getMinCoupon() {
		return minCoupon;
	}

	public void setMinCoupon(int minCoupon) {
		this.minCoupon = minCoupon;
	}

	public Date getValidity() {
		return validity;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}

	public int getMaxCoupon() {
		return maxCoupon;
	}

	public void setMaxCoupon(int maxCoupon) {
		this.maxCoupon = maxCoupon;
	}

}
