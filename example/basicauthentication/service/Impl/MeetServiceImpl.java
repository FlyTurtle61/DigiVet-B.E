package com.springsecurity.basicauthentication.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springsecurity.basicauthentication.Shared.dto.MeetingDto;
import com.springsecurity.basicauthentication.entities.Meeting;
import com.springsecurity.basicauthentication.repositories.MeetingRepository;
import com.springsecurity.basicauthentication.service.repositories.MeetService;

@Service
public class MeetServiceImpl implements MeetService {
	@Autowired
	private MeetingRepository meetingRepository;

	public MeetServiceImpl(MeetingRepository meetingRepository) {
		this.meetingRepository = meetingRepository;
	}

	public MeetingDto createMeet(MeetingDto meetingDto) {
		Meeting storedByMeeting = new Meeting();
		Meeting meetingEntity = new Meeting();
		MeetingDto returnValue = new MeetingDto();
		BeanUtils.copyProperties(meetingDto, meetingEntity);
		if(this.meetingRepository.findByMeetingDate(meetingEntity.getMeetingDate()) != null) throw new RuntimeException("the record is already exist");

		storedByMeeting = this.meetingRepository.save(meetingEntity);
		BeanUtils.copyProperties(storedByMeeting, returnValue);
		return returnValue;

	}

	@Override
	public List<MeetingDto> getAllVetMeet(MeetingDto meetingDto) {
		List<MeetingDto> returnValue = new ArrayList<MeetingDto>();
		Meeting entityMeetings = new Meeting();
		List<Meeting> storedByMeeting = new ArrayList<Meeting>();
		ModelMapper modelMapper = new ModelMapper();
		BeanUtils.copyProperties(meetingDto, entityMeetings);
		storedByMeeting = this.meetingRepository.findByVetMeeting(entityMeetings.getVetEmail());
		
		for(Meeting meet:storedByMeeting)
        {
            returnValue.add( modelMapper.map(meet, MeetingDto.class) );
        }        System.out.println(returnValue);
		return returnValue;
	}

	@Override
	public List<MeetingDto> getAllUserMeet(MeetingDto meetingDto) {
		List<MeetingDto> returnValue = new ArrayList<MeetingDto>();
		Meeting entityMeeting = new Meeting();
		List<Meeting> storedByMeeting = new ArrayList<Meeting>();
		ModelMapper modelMapper = new ModelMapper();
		
		BeanUtils.copyProperties(meetingDto, entityMeeting);
		storedByMeeting = this.meetingRepository.findByUserMeeting(entityMeeting.getUserEmail());

		for(Meeting meet:storedByMeeting)
        {
            returnValue.add( modelMapper.map(meet, MeetingDto.class) );
        }
		return returnValue;
	}

	@Override
	public MeetingDto deleteMeeting(int id) {
	
		this.meetingRepository.deleteById(id);
		return null;
	}

	@Override
	public MeetingDto updateMeeting(MeetingDto meetingDto) {
		Meeting storedByMeeting = new Meeting();
		Meeting meetingEntity = new Meeting();
		MeetingDto returnValue = new MeetingDto();
		BeanUtils.copyProperties(meetingDto, meetingEntity);
		if(this.meetingRepository.findByMeetingDate(meetingEntity.getMeetingDate()) != null) throw new RuntimeException("the record is already exist");

		storedByMeeting = this.meetingRepository.save(meetingEntity);
		BeanUtils.copyProperties(storedByMeeting, returnValue);
		return returnValue;
	}

}
