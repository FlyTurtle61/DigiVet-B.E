package com.digivet.ws.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.digivet.ws.entities.MeetingCanceled;
@Repository
public interface MeetCanceledRepository extends JpaRepository<MeetingCanceled, Integer> {
	@Query(value = "SELECT * FROM meeting_canceled where meeting_canceled.user_email = :userEmail",nativeQuery = true)
	List<MeetingCanceled> findByRecord(@Param("userEmail")String userEmail);
}
