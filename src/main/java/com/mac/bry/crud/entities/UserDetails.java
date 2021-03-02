package com.mac.bry.crud.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="User_Details")
	private Long id;
	
	private String detail;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	
	
	public UserDetails(Long id, String detail, User user) {
		super();
		this.id = id;
		this.detail = detail;
		this.user = user;
	}

	public UserDetails(Long id, String detail) {
		super();
		this.id = id;
		this.detail = detail;
	}

	public UserDetails() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
