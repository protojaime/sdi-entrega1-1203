package com.uniovi.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.*;
import com.uniovi.repositories.ProductsRepository;


@Service
public class ProductsService {

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private ProductsRepository ProductsRepository;

	public List<Product> getProducts() {
		
		List<Product> Products = new ArrayList<Product>();
		ProductsRepository.findAll().forEach(Products::add);
		return Products;
	}
	
	public void setProductSold(boolean revised,Long id){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		Product Product = ProductsRepository.findById(id).get();
		if(Product.getUser().getEmail().equals(email) ) {
		ProductsRepository.updateSold(revised, id);
		}
	}

	
	
	@SuppressWarnings("unchecked")
	public Product getProduct(Long id) {
		Set<Product> consultedList = (Set<Product>) httpSession.getAttribute("consultedList");
		if ( consultedList == null ) {
		consultedList = new HashSet<Product>();
		}
		Product ProductObtained = ProductsRepository.findById(id).get();
		consultedList.add(ProductObtained);
		httpSession.setAttribute("consultedList", consultedList);
		return ProductObtained;
	}


	
	
	
	public Page<Product> getProductsForUser(Pageable pageable, User user){
		Page<Product> Products = new PageImpl<Product>(new LinkedList<Product>());
	//	if ( user.getRole().equals("ROLE_USER")) {
		//Products = ProductsRepository.findAllByUser(pageable, user);	
		//if ( user.getRole().equals("ROLE_ADMIN")){
	    Products = getProducts(pageable);	
		return Products;
		}
	
		public Page<Product> searchProductsByDescriptionAndNameForUser (Pageable pageable, String searchText, User user){
		Page<Product> Products = new PageImpl<Product>(new LinkedList<Product>());
		searchText = "%"+searchText+"%";
	//	if ( user.getRole().equals("ROLE_USER")) {
	//	Products = ProductsRepository.searchByDescriptionNameAndUser(pageable, searchText, user);
	//	} 
		//if ( user.getRole().equals("ROLE_ADMIN")){
		Products = ProductsRepository.searchByDescriptionAndName(pageable, searchText);
		
		return Products;
		}
		public Page<Product> getProducts(Pageable pageable){
		Page<Product> Products = ProductsRepository.findAll(pageable);
		return Products;
		}

	
	
	public void addProduct(Product Product) {
// Si en Id es null le asignamos el ultimo + 1 de la lista
		ProductsRepository.save(Product);
	}

	public void deleteProduct(Long id) {
		ProductsRepository.deleteById(id);
	}
}