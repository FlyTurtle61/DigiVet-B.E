package com.springsecurity.basicauthentication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springsecurity.basicauthentication.entities.Comments;
@Repository
public interface CommentRepository extends JpaRepository<Comments, Integer> {
	@Query(value = "SELECT * FROM comment where comment.vet_email = :vetEmail",nativeQuery = true)
	List<Comments> findByVetEmail(@Param("vetEmail")String vetEmail);

}
