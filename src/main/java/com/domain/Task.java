package com.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Task {

	@Id
	@GeneratedValue
	private Long id;
	private String text;
	private String status;
	private Date dueDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="USER_ID")
	private User user;
	
	public Task() { //Required for Instantiation
		super();
	}
	
	public Task(Long id, String text, Date dueDate) {
		super();
		this.id = id;
		this.text = text;
		this.dueDate = dueDate;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
