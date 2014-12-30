package com.kalatag.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="country")
public class Country  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	@NotEmpty
	private String name;
	private String Code;
	
	@OneToMany(fetch=FetchType.EAGER, targetEntity = Province.class, mappedBy = "country")
	@JsonIgnore
	private Set<Province> provinces = new HashSet<Province>();	
	
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
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public Set<Province> getProvinces() {
		return provinces;
	}
	public void setProvinces(Set<Province> provinces) {
		this.provinces = provinces;
	}

}
