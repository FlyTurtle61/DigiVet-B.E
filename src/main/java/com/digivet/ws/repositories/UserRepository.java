package com.digivet.ws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.digivet.ws.entities.Users;
import com.digivet.ws.entities.Vet;



@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

	Users findByEmail(String email);
	Users findByFirstName(String email);
	@Query(value = "SELECT password FROM users where email = :email ",nativeQuery = true)
	String findByPassword(@Param("email")String email);
	@Query(value = "SELECT * FROM users where email =:email", nativeQuery = true)
	Users findByUserProfile(String email);
	
	
}
