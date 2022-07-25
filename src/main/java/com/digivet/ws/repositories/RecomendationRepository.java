package com.digivet.ws.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.digivet.ws.entities.Recommendation;

@Repository
public interface RecomendationRepository extends JpaRepository<Recommendation, Integer> {

	@Query(value  = "SELECT * FROM recommendation WHERE status = :status", nativeQuery = true)
	List<Recommendation> getAllStatus(@Param("status")String status);
	
	
}
