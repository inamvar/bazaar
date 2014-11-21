package com.dariksoft.bazaar.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotEmpty
	private String text;
	private Date date;
	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private Person author;
	private boolean isAccepted;

	@ManyToOne
	@JoinColumn(name = "deal_id", nullable = true)
	private Deal deal;

	@ManyToOne
	private Comment parent;
	@OneToMany(mappedBy = "parent")
	private Set<Comment> comments;

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Comment getParent() {
		return parent;
	}

	public void setParent(Comment parent) {
		this.parent = parent;
	}

	public Deal getDeal() {
		return deal;
	}

	public void setDeal(Deal deal) {
		this.deal = deal;
	}

}
