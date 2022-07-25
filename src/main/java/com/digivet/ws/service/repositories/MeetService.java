package com.digivet.ws.service.repositories;

import java.util.List;

import com.digivet.ws.Shared.dto.MeetingDto;



public interface MeetService {

	MeetingDto createMeet(MeetingDto meetingDto);
	List<MeetingDto> getAllVetMeet(MeetingDto meetingDto);
	List<MeetingDto> getAllUserMeet(MeetingDto meetingDto);
	MeetingDto deleteMeeting(MeetingDto meetingDto);
	MeetingDto updateMeeting(MeetingDto meetingDto);
	
	List<MeetingDto> getAllmeet();
	
}
