package com.dariksoft.bazaar.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Entity
@Table(name="person")
public class Person {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	@Column(nullable =false, name = "firstname")
	@Min(3)
	@Max(100)
	private String firstName;
	@Column(nullable =false, name = "lastname")
	@Min(3)
	@Max(100)
	private String lastName;
	private Date birthday;
	private Gender gender;
	
	@Column(nullable =false, name = "username",unique=true)	
	private String username;
	@Min(4)
	private String password;
	private Contact contact;
	private Account account;
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact address) {
		this.contact = address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	} 

}
