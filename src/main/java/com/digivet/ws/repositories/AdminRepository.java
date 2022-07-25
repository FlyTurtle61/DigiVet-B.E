package com.digivet.ws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.digivet.ws.entities.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	@Query(value = "SELECT password FROM admin where admin_email = :admin_email ",nativeQuery = true)
	String findByPassword(@Param("admin_email")String admin_email);
}
