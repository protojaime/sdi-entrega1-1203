package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.uniovi.entities.*;
import com.uniovi.services.ProductsService;
import com.uniovi.services.UsersService;

@Controller
public class ProductsController {
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired // Inyectar el servicio
	private ProductsService ProductsService;
	@Autowired
	private UsersService usersService;

	@RequestMapping("/product/list")
	public String getList(Model model, Pageable pageable, Principal principal, 
	@RequestParam(value = "", required=false) String searchText){
	String dni = principal.getName();
	User user = usersService.getUserByEmail(dni);

	Page<Product> Products = new PageImpl<Product>(new LinkedList<Product>());
	if (searchText != null && !searchText.isEmpty()) {
	Products = ProductsService
	.searchProductsByDescriptionAndNameForUser(pageable, searchText, user);
	if(Products.isEmpty()) {
		Products = ProductsService.getProductsForUser(pageable, user);
	}
	
	} else {
	Products = ProductsService.getProductsForUser(pageable, user);
	}
	model.addAttribute("productList", Products.getContent());
	model.addAttribute("page", Products);
	model.addAttribute("wallet", user.getWallet());
	return "product/list";
	}
	
	
	@RequestMapping("/product/bought")
	public String getBoughtList(Model model, Principal principal){
	String dni = principal.getName();
	User user = usersService.getUserByEmail(dni);
	model.addAttribute("productList", ProductsService.findAllBoughtByUser(user));
	model.addAttribute("wallet", user.getWallet());
	return "product/bought";
	}
	
	
	@RequestMapping("/product/list/update")
	public String updateList(Model model, Pageable pageable, Principal principal){
	String dni = principal.getName(); // Email es el name de la autenticación
	User user = usersService.getUserByEmail(dni);
	Page<Product> Products = ProductsService.getProductsForUser(pageable, user);
	model.addAttribute("wallet", user.getWallet());
	model.addAttribute("productList", Products.getContent() );
	return "product/list :: tableProducts";
	}
	
	
	
	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
	public String setProduct(@ModelAttribute Product Product) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
	User user=usersService.getUserByEmail(name);
	Product.setUser(user);
		ProductsService.addProduct(Product);
		return "redirect:/product/list";
	}

	@RequestMapping("/product/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String dni = auth.getName();       
		User activeUser = usersService.getUserByEmail(dni);
		model.addAttribute("wallet", activeUser.getWallet());
		model.addAttribute("product", ProductsService.getProduct(id));
		return "product/details";
	}

	@RequestMapping("/product/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		ProductsService.deleteProduct(id);
		return "redirect:/product/list";
	}

	@RequestMapping(value = "/product/add")
	public String getProduct(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String dni = auth.getName();       
		User activeUser = usersService.getUserByEmail(dni);
		model.addAttribute("wallet", activeUser.getWallet());
		model.addAttribute("usersList", usersService.getUsers());
		return "product/add";
	}

	@RequestMapping(value="/product/{id}/sold", method=RequestMethod.GET)
	public String setSoldTrue(Model model, @PathVariable Long id){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String dni = auth.getName();       
		User activeUser = usersService.getUserByEmail(dni);
	//ProductsService.setProductSold(true, id);
	boolean a= ProductsService.addProductToBroughtOfUserbyID(id,activeUser.getId());
	
	if (a) {
	return "redirect:/product/list";
	}
	else {
		return "redirect:/product/nomoney";
	}
	}
	@RequestMapping(value= "/product/nomoney", method=RequestMethod.GET)
	public String NoMoneyScreen(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String dni = auth.getName();       
		User activeUser = usersService.getUserByEmail(dni);
		model.addAttribute("wallet", activeUser.getWallet());
	
		return"/product/nomoney";
	}
	
	
	
	/*
	@RequestMapping(value="/product/{id}/nosold", method=RequestMethod.GET)
	public String setSoldFalse(Model model, @PathVariable Long id){
	ProductsService.setProductSold(false, id);
	return "redirect:/product/list";
	}
*/
	/*
	@RequestMapping(value = "/product/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id) {
		model.addAttribute("product", ProductsService.getProduct(id));
		model.addAttribute("usersList", usersService.getUsers());
		return "product/edit";
	}

	@RequestMapping(value = "/product/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute Product Product) {
		Product original = ProductsService.getProduct(id);
		original.setPrice(Product.getPrice());
		original.setDescription(Product.getDescription());
		ProductsService.addProduct(original);
		return "redirect:/product/details/" + id;
	}
*/

	public HttpSession getHttpSession() {
		return httpSession;
	}


	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}
}