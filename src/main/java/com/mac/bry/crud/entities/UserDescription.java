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
public class UserDescription implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="User_Descriptions")
	private Long id;
	
	private String userDescription;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	
	
	public UserDescription(Long id, String userDescription, User user) {
		super();
		this.id = id;
		this.userDescription = userDescription;
		this.user = user;
	}

	public UserDescription(Long id, String userDescription) {
		super();
		this.id = id;
		this.userDescription = userDescription;
	}

	public UserDescription() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserDescription [id=" + id + ", userDescription=" + userDescription + ", user=" + user + "]";
	}
	
	
}
