package com.springsecurity.basicauthentication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springsecurity.basicauthentication.entities.Users;


@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

	Users findByEmail(String email);
	Users findByFirstName(String email);
	Users findByPassword(String password);
	
}
