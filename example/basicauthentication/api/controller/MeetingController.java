package com.springsecurity.basicauthentication.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.basicauthentication.Shared.dto.MeetingDto;
import com.springsecurity.basicauthentication.entities.Meeting;
import com.springsecurity.basicauthentication.model.request.MeetingDetailsRequestModel;
import com.springsecurity.basicauthentication.model.response.MeetRest;
import com.springsecurity.basicauthentication.service.repositories.MeetService;

@RestController
@CrossOrigin
@RequestMapping(value = "/digivet/meeting")
public class MeetingController {

	@Autowired
	private MeetService meetService;
	public MeetingController( MeetService meetService)
	{
		this.meetService = meetService;
	}
	
	@PostMapping(value = "/createMeeting")
	public MeetRest createMeetController(@RequestBody MeetingDetailsRequestModel model)
	{
		MeetingDto meetingDto = new MeetingDto();
		MeetRest returnValue = new MeetRest();
		BeanUtils.copyProperties(model, meetingDto);
		MeetingDto storedByMeeting = new MeetingDto();
		storedByMeeting = this.meetService.createMeet(meetingDto);
		BeanUtils.copyProperties(storedByMeeting, returnValue);		
		return returnValue;
	}
	@PostMapping(value = "/getAllVetMeet")
	public List<MeetRest> getAllVetMeet(@RequestBody MeetingDetailsRequestModel model)
	{
		MeetingDto meetingDto = new MeetingDto();
		BeanUtils.copyProperties(model, meetingDto);
		List<MeetingDto> storedByMeeting = new ArrayList<MeetingDto>();
		storedByMeeting = this.meetService.getAllVetMeet(meetingDto);
		List<MeetRest> returnValue = new ArrayList<MeetRest>();
		ModelMapper mapper = new ModelMapper();
		for(MeetingDto meet:storedByMeeting)
        {
            returnValue.add( mapper.map(meet, MeetRest.class) );
        }		System.out.println(returnValue);

		return returnValue;
	}
	@PostMapping(value="/getAllUserMeet")
	public List<MeetRest> getAllUserMeet(@RequestBody MeetingDetailsRequestModel model)
	{
		MeetingDto meetingDto = new MeetingDto();
		BeanUtils.copyProperties(model, meetingDto);
		List<MeetingDto> storedByMeeting = new ArrayList<MeetingDto>();
		storedByMeeting = this.meetService.getAllUserMeet(meetingDto);
		List<MeetRest> returnValue = new ArrayList<MeetRest>();
		ModelMapper mapper = new ModelMapper();
		for(MeetingDto meet:storedByMeeting)
        {
            returnValue.add( mapper.map(meet, MeetRest.class) );
        }
		return returnValue;
	}
	@DeleteMapping(value = "/deleteMeet{id}")
	public MeetRest deleteMeet(@Param(value = "id") int id)
	{
		this.meetService.deleteMeeting(id);
		return null;
	}
	
	@PutMapping(value="/updateMeet")
	public MeetRest updateMeeting(MeetingDetailsRequestModel meetingDetailsRequestModel)
	{
		MeetingDto storedByMeetUpdate = new MeetingDto();
		MeetRest returnValue = new MeetRest();
		MeetingDto dto = new MeetingDto();
		BeanUtils.copyProperties(meetingDetailsRequestModel,dto);
		storedByMeetUpdate = this.meetService.updateMeeting(dto);
		BeanUtils.copyProperties(storedByMeetUpdate, returnValue);
		return returnValue;
	}
	
	
	

}
