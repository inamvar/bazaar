package com.dariksoft.bazaar.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Province {


	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String name;
	
	@ManyToOne
	@JoinColumn(name="country_id", nullable=true)
	private Country country;
	
	@OneToMany(targetEntity = City.class, mappedBy = "province")
    private Set<City> cities = new HashSet<City>();

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
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

	public Set<City> getCities() {
		return cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

}
