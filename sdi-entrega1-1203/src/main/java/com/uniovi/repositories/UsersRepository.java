package com.uniovi.repositories;

import com.uniovi.entities.*;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UsersRepository extends CrudRepository<User, Long> {
	
	User findByEmail(String email);

	
	@Modifying
@Transactional
@Query("UPDATE User SET wallet = ?1 WHERE id = ?2")
	void UpdateWallet(Double current, long id);


}