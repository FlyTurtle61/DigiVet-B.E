package com.springsecurity.basicauthentication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springsecurity.basicauthentication.entities.Vet;


@Repository
public interface VetRepository extends JpaRepository<Vet, Integer > {
	Vet findByEmail(String email);
	Vet findByPassword(String Password);

	
}
