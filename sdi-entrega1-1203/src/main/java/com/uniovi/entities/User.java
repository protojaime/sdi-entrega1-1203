package com.uniovi.entities;

import javax.persistence.*;
import java.util.Set; //A collection that contains no duplicate elements

@Entity
public class User {
	@Id
	@GeneratedValue
	private long id;
	private String role;

	@Column(unique = true)
	private String dni;
	private String name;
	private String lastName;
	private String Email;
	private Double Wallet;
	
	public Double getWallet() {
		return Wallet;
	}

	public void setWallet(Double wallet) {
		Wallet = wallet;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	private String password;
	@Transient // propiedad que no se almacena e la tabla.
	private String passwordConfirm;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Mark> marks;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Product> Products;

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
	public User(String dni, String name, String lastName) {
		super();
		this.dni = dni;
		this.name = name;
		this.lastName = lastName;
		this.Wallet=100.0;
	}
	public User(String dni, String name, String lastName,String email) {
		super();
		this.dni = dni;
		this.name = name;
		this.lastName = lastName;
		this.Email=email;
		this.Wallet=100.0;
		
	}
	public User(String dni, String name, String lastName,String email, Double Wallet) {
		super();
		this.dni = dni;
		this.name = name;
		this.lastName = lastName;
		this.Email=email;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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

	public void setMarks(Set<Mark> marks) {
		this.marks = marks;
	}

	public Set<Mark> getMarks() {
		return marks;
	}

	public String getFullName() {
		return this.name + " " + this.lastName;
	}

	public void setProducts(Set<Product> Products) {
		this.Products = Products;
	}
	public Set<Mark> getProducts() {
		return marks;
	}
}