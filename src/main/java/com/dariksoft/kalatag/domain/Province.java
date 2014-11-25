package com.dariksoft.kalatag.domain;

import java.util.HashSet;
import java.util.Set;

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
public class Province {


	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	@NotEmpty
	private String name;
	
	@ManyToOne(fetch=FetchType.EAGER)
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
	public void setId(int id) {
		this.id = id;
	}

	public Set<City> getCities() {
		return cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

}
