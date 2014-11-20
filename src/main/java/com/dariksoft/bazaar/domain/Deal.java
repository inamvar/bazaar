package com.dariksoft.bazaar.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Deal {


	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="item_id", nullable=false)
	private Item item;	
	
	@ManyToOne
	@JoinColumn(name="contact_id", nullable=false)
	private Contact location;
	
	@OneToMany(targetEntity = DealOption.class, mappedBy = "deal")
	private Set<DealOption> options;
	
	@OneToMany(targetEntity = Comment.class, mappedBy = "deal")
	private Set<Comment> comments;

	public int getId() {
		return id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Contact getLocation() {
		return location;
	}

	public void setLocation(Contact location) {
		this.location = location;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}


}
