package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import com.uniovi.entities.Product;
import com.uniovi.entities.User;
public interface ProductsRepository extends CrudRepository<Product, Long>{
	@Query("SELECT r FROM Product r WHERE (LOWER(r.description) LIKE LOWER(?1) OR LOWER(r.user.name) LIKE LOWER(?1))")
			Page<Product> searchByDescriptionAndName(Pageable pageable, String seachtext);
			@Query("SELECT r FROM Product r WHERE (LOWER(r.description) LIKE LOWER(?1) OR LOWER(r.user.name) LIKE LOWER(?1)) AND r.user = ?2 ")
			Page<Product> searchByDescriptionNameAndUser(Pageable pageable, String seachtext, User user);
			@Query("SELECT r FROM Product r WHERE r.user = ?1 ORDER BY r.id ASC ")
			Page<Product> findAllByUser(Pageable pageable, User user);
			Page<Product> findAll(Pageable pageable); 

	@Modifying
	@Transactional
	@Query("UPDATE Product SET sold = ?1 WHERE id = ?2")
	void updateSold(Boolean resend, Long id);

}
