package com.springsecurity.basicauthentication.service.repositories;

import java.util.List;

import com.springsecurity.basicauthentication.Shared.dto.MeetingDto;
import com.springsecurity.basicauthentication.entities.Meeting;

public interface MeetService {

	MeetingDto createMeet(MeetingDto meetingDto);
	List<MeetingDto> getAllVetMeet(MeetingDto meetingDto);
	List<MeetingDto> getAllUserMeet(MeetingDto meetingDto);
	MeetingDto deleteMeeting(int id);
	MeetingDto updateMeeting(MeetingDto meetingDto);
	
	
}
