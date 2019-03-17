package com.uniovi.entities;

import javax.persistence.*;
import java.util.Set; //A collection that contains no duplicate elements

@Entity
public class User {
	@Id
	@GeneratedValue
	private long id;
	private String role;
	private String name;
	private String lastName;
	@Column(unique = true)
	private String email;
	private Double Wallet;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Product> Products;

	@OneToMany(mappedBy = "buyeruser", cascade = CascadeType.ALL)
	private Set<Product> BoughtProducts;

	
	public Set<Product> getBoughtProducts() {
		return BoughtProducts;
	}

	public void setBoughtProducts(Set<Product> boughtProducts) {
		BoughtProducts = boughtProducts;
	}

	public Double getWallet() {
		return Wallet;
	}

	public void setWallet(Double wallet) {
		Wallet = wallet;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String password;
	@Transient // propiedad que no se almacena e la tabla.
	private String passwordConfirm;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getRole() {
		return role;
		}
	public void setRole(String role) {
		this.role = role;
		}

	
	public String getPasswordConfirm() {

		return passwordConfirm;

	}

	public void setPasswordConfirm(String passwordConfirm) {

		this.passwordConfirm = passwordConfirm;

	}
	
	public User( String name, String lastName,String email) {
		super();

		this.name = name;
		this.lastName = lastName;
		this.email=email;
		this.Wallet=100.0;
		
	}
	public User( String name, String lastName,String email, Double Wallet) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email=email;
		this.Wallet=Wallet;
	}
		
		
	
	

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getFullName() {
		return this.name + " " + this.lastName;
	}

	public void setProducts(Set<Product> Products) {
		this.Products = Products;
	}
	public Set<Product> getProducts() {
		return Products;
	}
}