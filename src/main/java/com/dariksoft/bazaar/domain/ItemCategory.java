package com.dariksoft.bazaar.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "item_category")
public class ItemCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false, name = "cat_name")
	@NotEmpty
	private String name;
	private String description;

	@OneToMany(targetEntity = Deal.class, mappedBy = "category")
	private Set<Deal> deal;

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

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Set<Deal> getDeals() {
		return deal;
	}

	public void setDeals(Set<Deal> deals) {
		this.deal = deals;
	}

}
