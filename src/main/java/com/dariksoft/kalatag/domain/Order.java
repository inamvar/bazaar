package com.dariksoft.kalatag.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Order(){
		super();
		this.setCoupons(new ArrayList<Coupon>());
	}
	
	public Order(Deal deal,DealOption option, Person customer, int qty) {
		super();
		if(deal !=null && customer !=null){
				
				this.setOrderDate(new Date());
				this.setPerson(customer);
				this.setDeal(deal);
				this.setCoupons(new ArrayList<Coupon>());
				this.setQuantity(qty);
				this.setTotalPrice(deal.getPrice() * qty);
				this.setStatus(OrderStatus.PENDING);
				if(option != null){
					this.setOption(option);
					this.setTotalPrice((deal.getPrice() - ( (option.getDiscount()/100) * deal.getPrice() )) * qty);
				}
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int quantity;
	@OneToMany(targetEntity = Coupon.class, mappedBy = "order", cascade= CascadeType.ALL)
	private List<Coupon> coupons;
	
	private Date orderDate;	
	private Date finilizeDate;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Person person;
	
	private String note;

	@ManyToOne
	@JoinColumn(name = "deal_id", nullable = false)
	private Deal deal;

	@ManyToOne
	@JoinColumn(name = "deal_option_id", nullable = true)
	private DealOption option;
	
	
	@OneToOne(optional = true)
	@JoinColumn(name = "payment_id", unique = true, nullable = true)
	private Payment payment;
	private OrderStatus status;
	private double totalPrice;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Deal getDeal() {
		return deal;
	}

	public void setDeal(Deal deal) {
		this.deal = deal;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	public DealOption getOption() {
		return option;
	}

	public void setOption(DealOption option) {
		this.option = option;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getFinilizeDate() {
		return finilizeDate;
	}

	public void setFinilizeDate(Date finilizeDate) {
		this.finilizeDate = finilizeDate;
	}


}
