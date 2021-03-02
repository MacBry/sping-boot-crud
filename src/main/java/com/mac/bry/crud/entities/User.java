package com.mac.bry.crud.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="User_ID")
    private long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    
    @NotBlank(message = "Email is mandatory")
    private String email;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    //@JoinColumn(name="User_Details",referencedColumnName = "User_ID")
    private List<UserDetails> userDetails = new ArrayList<>();

    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    

    public List<UserDetails> getUserDetails() {
		return userDetails;
	}
    
    public void addDetail (UserDetails detail) {
    	this.userDetails.add(detail);
    }

	public void setUserDetails(List<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}

	@Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + '}';
    }
}
