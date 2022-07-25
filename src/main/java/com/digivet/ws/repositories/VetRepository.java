package com.digivet.ws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digivet.ws.entities.Vet;



@Repository
public interface VetRepository extends JpaRepository<Vet, Integer > {
	Vet findByEmail(String email);
	@Query(value = "SELECT password FROM vet where email = :email ",nativeQuery = true)
	String findByPassword(String email);
	@Query(value = "SELECT * FROM vet where email =:email", nativeQuery = true)
	Vet findByVetProfile(String email);

	
}
