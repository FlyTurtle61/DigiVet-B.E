package com.digivet.ws.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.digivet.ws.Shared.dto.CommentDto;
import com.digivet.ws.Shared.dto.MeetingDto;
import com.digivet.ws.config.MailSenderConfig;
import com.digivet.ws.entities.Comments;
import com.digivet.ws.entities.Meeting;
import com.digivet.ws.repositories.MeetingRepository;
import com.digivet.ws.service.repositories.MeetService;

@Service
public class MeetServiceImpl implements MeetService {

	Logger logger = LogManager.getLogger(CommentServiceImpl.class);

	@Autowired
	private MailSenderConfig config;
	@Autowired
	private MeetingRepository meetingRepository;

	public MeetServiceImpl(MeetingRepository meetingRepository) {
		this.meetingRepository = meetingRepository;
	}

	public MeetingDto createMeet(MeetingDto meetingDto) {
		Meeting storedByMeeting = new Meeting();
		Meeting meetingEntity = new Meeting();
		MeetingDto returnValue = new MeetingDto();
		try {
			BeanUtils.copyProperties(meetingDto, meetingEntity);
			if (this.meetingRepository.findByMeetingDate(meetingEntity.getMeetingDate()) != null)
				throw new RuntimeException("Tarih bu tarih dolu");

			storedByMeeting = this.meetingRepository.save(meetingEntity);
			BeanUtils.copyProperties(storedByMeeting, returnValue);

			config.vetMeetSendMail(returnValue);
			config.userMeetSendMail(returnValue);
			logger.info("createMeet service is invoke");

		} catch (Exception ex) {
			logger.error("createMeet service is not invoke error message:" + ex.getMessage());

		}

		return returnValue;

	}

	@Override
	public List<MeetingDto> getAllVetMeet(MeetingDto meetingDto) {
		List<MeetingDto> returnValue = new ArrayList<MeetingDto>();
		Meeting entityMeetings = new Meeting();
		List<Meeting> storedByMeeting = new ArrayList<Meeting>();
		ModelMapper modelMapper = new ModelMapper();

		try {
			logger.info("getAllVetMeet service is invoke");

			BeanUtils.copyProperties(meetingDto, entityMeetings);
			storedByMeeting = this.meetingRepository.findByVetMeeting(entityMeetings.getVetEmail());

			for (Meeting meet : storedByMeeting) {
				returnValue.add(modelMapper.map(meet, MeetingDto.class));
			}
		} catch (Exception ex) {
			logger.error("getAllVetMeet service is not invoke error message:" + ex.getMessage());

		}
		return returnValue;
	}

	@Override
	public List<MeetingDto> getAllUserMeet(MeetingDto meetingDto) {
		List<MeetingDto> returnValue = new ArrayList<MeetingDto>();
		Meeting entityMeeting = new Meeting();
		List<Meeting> storedByMeeting = new ArrayList<Meeting>();
		ModelMapper modelMapper = new ModelMapper();

		try {
			logger.info("getAllUserMeet service is invoke");

			BeanUtils.copyProperties(meetingDto, entityMeeting);
			storedByMeeting = this.meetingRepository.findByUserMeeting(entityMeeting.getUserEmail());

			for (Meeting meet : storedByMeeting) {
				returnValue.add(modelMapper.map(meet, MeetingDto.class));
			}
		} catch (Exception ex) {
			logger.error("getAllUserMeet service is not invoke error message:" + ex.getMessage());
		}
		return returnValue;
	}

	@Override
	public MeetingDto deleteMeeting(MeetingDto meetingDto) {

		
		Meeting meetEntity = new Meeting();
		try {
			BeanUtils.copyProperties(meetingDto, meetEntity);
			int id = meetEntity.getId();
			logger.info("deleteMeeting service is invoke");

			this.meetingRepository.deleteById(id);
		} catch (Exception ex) {
			logger.error("deleteMeeting service is not invoke error message:" + ex.getMessage());
		}

		return null;
	}

	@Override
	public MeetingDto updateMeeting(MeetingDto meetingDto) {
		Meeting storedByMeeting = new Meeting();
		Meeting meetingEntity = new Meeting();
		MeetingDto returnValue = new MeetingDto();
		try {
			logger.info("updateMeeting service is invoke");

			BeanUtils.copyProperties(meetingDto, meetingEntity);
			if (this.meetingRepository.findByMeetingDate(meetingEntity.getMeetingDate()) != null)
				throw new RuntimeException("the record is already exist");

			storedByMeeting = this.meetingRepository.save(meetingEntity);
			BeanUtils.copyProperties(storedByMeeting, returnValue);
		}catch (Exception ex) {
			logger.error("updateMeeting service is not invoke error message:" + ex.getMessage());
		}
		
		return returnValue;
	}

	@Override
	public List<MeetingDto> getAllmeet() {
		List<MeetingDto> returnValue = new ArrayList<MeetingDto>();
		try {
			List<Meeting> storedByData = this.meetingRepository.findAll();
			ModelMapper modelMapper = new ModelMapper();
			for (Meeting meet : storedByData) {
				returnValue.add(modelMapper.map(meet, MeetingDto.class));
			}
			logger.debug("getAllVet service is invoke");
		}catch(Exception ex) {
			logger.error("getAll Service is not invoked error :"+ex.getMessage());
		}
		return returnValue;
	}

}
