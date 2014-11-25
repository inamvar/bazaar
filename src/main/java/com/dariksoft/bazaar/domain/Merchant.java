package com.dariksoft.bazaar.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Merchant {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="contact_point_id", nullable=true)
	private Person contactPoint;
	@NotEmpty
	private String name;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="contact_id", nullable=true)
	private Contact contact;
	
	@OneToMany(targetEntity = Deal.class, mappedBy = "merchant")
	private Set<Deal> items;
	public Person getContactPoint() {
		return contactPoint;
	}
	public void setContactPoint(Person contactPoint) {
		this.contactPoint = contactPoint;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public Set<Deal> getItems() {
		return items;
	}
	public void setItems(Set<Deal> items) {
		this.items = items;
	}

}
