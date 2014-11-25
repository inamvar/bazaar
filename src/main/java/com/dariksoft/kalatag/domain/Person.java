package com.dariksoft.kalatag.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotEmpty
	@Column(nullable = false, name = "firstname")
	private String firstName;
	@NotEmpty
	@Column(nullable = false, name = "lastname")
	private String lastName;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	@Past
	private Date birthday;
	private Gender gender;
	@Column(nullable = false, name = "username", unique = true)
	private String username;
	private String password;

	@OneToOne(optional = true, mappedBy = "person")
	private Account account;



	
	
	@OneToMany(targetEntity = Comment.class, mappedBy = "author")
	private Set<Comment> Comments;



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
	public void setId(int id) {
		this.id = id;
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

	public Set<Comment> getComments() {
		return Comments;
	}

	public void setComments(Set<Comment> comments) {
		Comments = comments;
	}



}
