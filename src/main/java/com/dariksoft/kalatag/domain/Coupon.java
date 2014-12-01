package com.dariksoft.kalatag.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

import com.dariksoft.kalatag.util.BarcodeUtil;

@Entity
public class Coupon implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotEmpty
	private String code;
	
	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;
	
	@Column(length = 10000000)
	private byte[] qrcode;
	@Column(length = 10000000)
	private byte[] barcode;
	
	private Date expireDate;
	
	private Date issueDate;
	
	private CouponStatus status;
	
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "deal_id", nullable = false)
	private Deal deal;
	


	public Coupon() {
		super();
		this.code = UUID.randomUUID().toString().substring(0, 18).toUpperCase();
		this.barcode = BarcodeUtil.byteArrayBarcode(code);
		this.qrcode = BarcodeUtil.byteArrayQrCode(code);
		this.issueDate = new Date();
		this.status = CouponStatus.NOT_REDEMED;
	}

	
	public CouponStatus getStatus() {
		return status;
	}

	public void setStatus(CouponStatus status) {
		this.status = status;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public byte[] getBarcode() {
		return barcode;
	}

	public void setBarcode(byte[] barcode) {
		this.barcode = barcode;
	}

	public byte[] getQrcode() {
		return qrcode;
	}

	public void setQrcode(byte[] qrcode) {
		this.qrcode = qrcode;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Deal getDeal() {
		return deal;
	}

	public void setDeal(Deal deal) {
		this.deal = deal;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}

}
