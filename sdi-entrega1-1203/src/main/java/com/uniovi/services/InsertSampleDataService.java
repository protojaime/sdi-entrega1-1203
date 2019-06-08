package com.uniovi.services;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Product;
import com.uniovi.entities.User;
import com.uniovi.repositories.ProductsRepository;

@Service
public class InsertSampleDataService {

	
	@Autowired
	private UsersService usersService;
	@SuppressWarnings("unused")
	@Autowired
	private ProductsService ProductsService;
	@Autowired
	private ProductsRepository ProductsRepository;
	@Autowired
	private RolesService rolesService;
	@PostConstruct
	public void init() {
		
		
		
	User user1 = new User( "Pedro", "Díaz","testEmail1@Gmail.com",333.0);
	user1.setPassword("123456");
	user1.setRole(rolesService.getRoles()[0]);
	User user2 = new User("Lucas", "Núñez","testEmail2@Gmail.com");
	user2.setPassword("123456");
	user2.setRole(rolesService.getRoles()[0]);
	User user3 = new User( "María", "Rodríguez","testEmail3@Gmail.com");
	user3.setPassword("123456");
	user3.setRole(rolesService.getRoles()[0]);
	User user4 = new User("Marta", "Almonte","testEmail4@Gmail.com");
	user4.setPassword("123456");
	user4.setRole(rolesService.getRoles()[0]);
	User user5 = new User( "Pelayo", "Valdes","testEmail6@Gmail.com");
	user5.setPassword("123456");
	user5.setRole(rolesService.getRoles()[0]);
	User user6 = new User("Edward", "Núñez","Admin@Gmail.com");
	user6.setPassword("123456");
	user6.setRole(rolesService.getRoles()[1]);
	
	
		Set user1Products = new HashSet<Product>() {
			{
				add(new Product("Producto 1","Descripción producto 1",  10.0, user1));
				add(new Product("Producto 2","Descripción producto 2",  30.0, user1));
				add(new Product("Producto 3","Descripción producto 3",  20.0, user1));
				add(new Product("Producto 4","Descripción producto 4",  50.0, user1));
			}
		};
		user1.setProducts(user1Products);
		

		
		
		
		Set user2Products = new HashSet<Product>() {
			{
				add(new Product("Producto 5","Descripción producto 5",  12.0, user2));
				add(new Product("Producto 6","Descripción producto 6",  320.0, user2));
				add(new Product("Producto 7","Descripción producto 7",  22.0, user2));
				add(new Product("Producto 8","Descripción producto 8",  5.0, user2));
			}
		};
		user2.setProducts(user2Products);
		
		
		
		Set user3Products = new HashSet<Product>() {
			{
				;
				add(new Product("Producto 9","Descripción producto 9",  2.0, user3));
				add(new Product("Producto 10","Descripción producto 10",  30.0, user3));
				add(new Product("Producto 11","Descripción producto 11",  32.0, user3));
				add(new Product("Producto 12","Descripción producto 12",  556.0, user3));
			}
		};
		user3.setProducts(user3Products);
		
		
		Set user4Products = new HashSet<Product>() {
			{
				add(new Product("Producto 13","Descripción producto 13",  24.0, user4));
				add(new Product("Producto 14","Descripción producto 14",  1.0, user4));
				add(new Product("Producto 15","Descripción producto 15",  2.20, user4));
				add(new Product("Producto 16","Descripción producto 16",  55.30, user4));
			}
			
			
		};
		
		user4.setProducts(user4Products);
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		
		Product P1= ProductsService.searchByDescriptionNameAndUser("Producto 13", user4).get(0);
		P1.setBuyeruser(user1);
		P1.setSold(true);
		ProductsRepository.save(P1);
		
		Product P2= ProductsService.searchByDescriptionNameAndUser("Producto 9", user3).get(0);
		P2.setBuyeruser(user1);
		P2.setSold(true);
		ProductsRepository.save(P2);
		
		Product P3= ProductsService.searchByDescriptionNameAndUser("Producto 5", user2).get(0);
		P3.setBuyeruser(user1);
		P3.setSold(true);
		ProductsRepository.save(P3);
		
		Product P4= ProductsService.searchByDescriptionNameAndUser("Producto 14", user4).get(0);
		P4.setBuyeruser(user2);
		P4.setSold(true);
		ProductsRepository.save(P4);
		
		
	}
}