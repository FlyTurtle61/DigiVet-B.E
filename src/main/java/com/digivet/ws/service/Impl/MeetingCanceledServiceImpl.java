package com.digivet.ws.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.digivet.ws.Shared.dto.CommentDto;
import com.digivet.ws.Shared.dto.MeetingDto;
import com.digivet.ws.Shared.dto.UserDto;
import com.digivet.ws.Shared.dto.VetMeetingCanceledDto;
import com.digivet.ws.config.MailSenderConfig;
import com.digivet.ws.entities.MeetingCanceled;
import com.digivet.ws.model.request.MeetingCanceledRequestModel;
import com.digivet.ws.model.response.CommentRest;
import com.digivet.ws.repositories.MeetCanceledRepository;
import com.digivet.ws.repositories.MeetingRepository;
import com.digivet.ws.service.repositories.MeetCanceledService;
import com.digivet.ws.service.repositories.MeetService;
@Service
public class MeetingCanceledServiceImpl implements MeetCanceledService {

	Logger logger = LogManager.getLogger(CommentServiceImpl.class);
	
	@Autowired
	private MeetCanceledRepository canceledRepository;
	@Autowired
	private MailSenderConfig config;
	@Autowired
	private MeetServiceImpl serviceImpl;
	@Override
	public VetMeetingCanceledDto createRecord(VetMeetingCanceledDto canceledDto) {

		MeetingCanceled storedByData = new MeetingCanceled();
		MeetingCanceled entity = new MeetingCanceled();
		VetMeetingCanceledDto returnValue = new VetMeetingCanceledDto();
		MeetingDto dto = new MeetingDto();
		try {
			System.out.println("id :"+canceledDto.getId()); 
			
			dto.setId(canceledDto.getId());
			this.serviceImpl.deleteMeeting(dto);
			BeanUtils.copyProperties(canceledDto, entity);
			
			storedByData = this.canceledRepository.save(entity);
			BeanUtils.copyProperties(storedByData, returnValue);
			
			logger.info("createRecord service is invoke");		
			//MailSenderConfig mailSenderConfig = new MailSenderConfig();
			config.sendMail(returnValue);
		}catch(Exception e) {
			logger.error("createRecord service is not invoke error:"+e.getMessage());
		}
		
		return returnValue;
	}
	
	@Override
	public List<VetMeetingCanceledDto> findAllMethod(VetMeetingCanceledDto canceledDto) {
		
		
		MeetingCanceled entity = new MeetingCanceled();
		List<VetMeetingCanceledDto> returnValue = new ArrayList<VetMeetingCanceledDto>();
		
		try {
			BeanUtils.copyProperties(canceledDto, entity);
			String email = entity.getUserEmail().toString();
			List<MeetingCanceled> storedByData  = this.canceledRepository.findByRecord(email);
			ModelMapper modelMapper = new ModelMapper();
			for (MeetingCanceled data : storedByData) {
				returnValue.add(modelMapper.map(data, VetMeetingCanceledDto.class));
			}			
			logger.info("getAllRecord service is invoke");
		}catch(Exception e) {
			logger.error("getAllRecord service is not invoke");
		}
		
		return returnValue;
		
	}

	@Override
	public List<VetMeetingCanceledDto> findAll() {
		List<VetMeetingCanceledDto> returnValue = new ArrayList<VetMeetingCanceledDto>();
		try {
			List<MeetingCanceled> storedByData = this.canceledRepository.findAll();
			ModelMapper modelMapper = new ModelMapper();
			for (MeetingCanceled data : storedByData) {
				returnValue.add(modelMapper.map(data, VetMeetingCanceledDto.class));
			}			
			logger.info("findAll service is invoke");
		}catch (Exception e) {
			logger.error("findAll service is not invoke");
		}
		return returnValue;
	}
	
	  
	}
	


