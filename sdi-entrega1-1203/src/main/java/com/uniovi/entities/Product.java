package com.uniovi.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	private Double price;
	private Date DueToDate;
	private Boolean sold;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name = "buyeruser_id")
	private User buyeruser;
	
	
	
	public User getBuyeruser() {
		return buyeruser;
	}

	public void setBuyeruser(User buyeruser) {
		this.buyeruser = buyeruser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDueToDate() {
		return DueToDate;
	}

	public void setDueToDate(Date dueToDate) {
		DueToDate = dueToDate;
	}

	
	
	public Boolean getSold() {
		return sold;
	}

	public void setSold(Boolean sold) {
		this.sold = sold;
	}




	
	public Product(Long id, String description, Double price) {
		super();
		this.id = id;
		this.description = description;
		this.price = price;
	}

	public Product(String description, Double price, User user) {
		super();
		this.description = description;
		this.price = price;
		this.user = user;
	}

	public Product() {
		
	}

	public Product(String name,String description, Double price, User user) {
		super();
		this.name=name;
		this.description = description;
		this.price = price;
		this.user = user;
		this.sold = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	
	
}