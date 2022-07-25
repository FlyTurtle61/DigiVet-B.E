package com.springsecurity.basicauthentication.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springsecurity.basicauthentication.entities.Meeting;


@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Integer> {

	@Query(value = "SELECT * FROM meeting WHERE meeting.vet_email = :vetEmail",nativeQuery = true)
	List<Meeting> findByVetMeeting(@Param("vetEmail") String vetEmail);
	
	@Query(value = "SELECT * FROM meeting WHERE meeting.user_email = :userEmail",nativeQuery = true)
	List<Meeting> findByUserMeeting(@Param("userEmail") String userEmail);
	
	Meeting findByMeetingDate(String meetingDate);
}
