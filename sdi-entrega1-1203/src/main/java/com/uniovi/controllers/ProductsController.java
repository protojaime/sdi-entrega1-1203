package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
	String dni = principal.getName(); // DNI es el name de la autenticación
	User user = usersService.getUserByDni(dni);
	Page<Product> Products = new PageImpl<Product>(new LinkedList<Product>());
	if (searchText != null && !searchText.isEmpty()) {
	Products = ProductsService
	.searchProductsByDescriptionAndNameForUser(pageable, searchText, user);
	} else {
	Products = ProductsService.getProductsForUser(pageable, user);
	}
	model.addAttribute("ProductList", Products.getContent());
	model.addAttribute("page", Products);

	return "Product/list";
	}
	
	
	@RequestMapping("/product/list/update")
	public String updateList(Model model, Pageable pageable, Principal principal){
	String dni = principal.getName(); // DNI es el name de la autenticación
	User user = usersService.getUserByDni(dni);
	Page<Product> Products = ProductsService.getProductsForUser(pageable, user);
	model.addAttribute("ProductList", Products.getContent() );
	return "Product/list :: tableProducts";
	}
	
	
	
	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
	public String setProduct(@ModelAttribute Product Product) {
		ProductsService.addProduct(Product);
		return "redirect:/product/list";
	}

	@RequestMapping("/product/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("Product", ProductsService.getProduct(id));
		return "Product/details";
	}

	@RequestMapping("/product/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		ProductsService.deleteProduct(id);
		return "redirect:/product/list";
	}

	@RequestMapping(value = "/product/add")
	public String getProduct(Model model) {
		model.addAttribute("usersList", usersService.getUsers());
		return "Product/add";
	}

	@RequestMapping(value="/product/{id}/resend", method=RequestMethod.GET)
	public String setResendTrue(Model model, @PathVariable Long id){
	ProductsService.setProductResend(true, id);
	return "redirect:/product/list";
	}
	@RequestMapping(value="/product/{id}/noresend", method=RequestMethod.GET)
	public String setResendFalse(Model model, @PathVariable Long id){
	ProductsService.setProductResend(false, id);
	return "redirect:/product/list";
	}

	
	@RequestMapping(value = "/product/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id) {
		model.addAttribute("Product", ProductsService.getProduct(id));
		model.addAttribute("usersList", usersService.getUsers());
		return "Product/edit";
	}

	@RequestMapping(value = "/product/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute Product Product) {
		Product original = ProductsService.getProduct(id);
		
// modificar solo score y description
		
		original.setScore(Product.getScore());
		original.setDescription(Product.getDescription());
		ProductsService.addProduct(original);
		return "redirect:/product/details/" + id;
	}


	public HttpSession getHttpSession() {
		return httpSession;
	}


	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}
}