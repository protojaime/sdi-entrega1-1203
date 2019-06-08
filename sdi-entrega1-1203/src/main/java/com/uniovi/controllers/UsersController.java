package com.uniovi.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.uniovi.entities.*;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {
@Autowired
private RolesService rolesService;

	@Autowired
	private UsersService usersService;
	@Autowired
	private SecurityService securityService;
	@Autowired
	private SignUpFormValidator signUpFormValidator;
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
	model.addAttribute("user", new User());
	 
	return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute @Validated User user, BindingResult result, Model 
	model) {
	 
	signUpFormValidator.validate(user, result);
	 
	if (result.hasErrors()) {
	 
	return "signup";
	 
	}
	 
	user.setRole(rolesService.getRoles()[0]);
	 
	usersService.addUser(user);
	 
	securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
	 
	return "redirect:home";
	}


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String dni = auth.getName();       
		User activeUser = usersService.getUserByEmail(dni);
		model.addAttribute("wallet", activeUser.getWallet());
		model.addAttribute("productList", activeUser.getProducts());

		return "home";
	}

	@RequestMapping("/user/list")
	public String getListado(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String dni = auth.getName();       
		User activeUser = usersService.getUserByEmail(dni);
		model.addAttribute("wallet", activeUser.getWallet());		
		model.addAttribute("usersList", usersService.getUsers());
		model.addAttribute("tobedeleted", new ArrayList<Long>()   );
		return "user/list";
	}
	
	
	@RequestMapping(value = "/user/massdeletion", method = RequestMethod.POST)
	public String massDeleteUsers(@ModelAttribute List<Long> userList) {
		for(Long l:userList) {
			usersService.deleteUser(l);
		}
		
		return "redirect:/user/list";
	}
	
	
	
	@RequestMapping(value="/user/add")
	public String getUser(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String dni = auth.getName();       
		User activeUser = usersService.getUserByEmail(dni);
		model.addAttribute("wallet", activeUser.getWallet());
	model.addAttribute("rolesList", rolesService.getRoles());
	return "user/add";
	}

	

	
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String setUser(@ModelAttribute User user) {
		usersService.addUser(user);
		return "redirect:/user/list";
	}

	@RequestMapping("/user/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String dni = auth.getName();       
		User activeUser = usersService.getUserByEmail(dni);
		model.addAttribute("wallet", activeUser.getWallet());
		model.addAttribute("user", usersService.getUser(id));
		return "user/details";
	}

	@RequestMapping("/user/delete/{id}")
	public String delete(@PathVariable Long id) {
		usersService.deleteUser(id);
		return "redirect:/user/list";
	}

	@RequestMapping(value = "/user/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id) {
		User user = usersService.getUser(id);
		model.addAttribute("wallet", user.getWallet());
		model.addAttribute("user", user);
		return "user/edit";
	}

	@RequestMapping(value = "/user/edit/{id}", method = RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute User user) {
		user.setId(id);
		usersService.addUser(user);
		return "redirect:/user/details/" + id;
	}
}