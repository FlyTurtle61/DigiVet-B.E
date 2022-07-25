package com.digivet.ws.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digivet.ws.Shared.dto.VetMeetingCanceledDto;
import com.digivet.ws.entities.MeetingCanceled;
import com.digivet.ws.model.request.MeetingCanceledRequestModel;
import com.digivet.ws.model.response.meetingCanceledRest;
import com.digivet.ws.service.Impl.CommentServiceImpl;
import com.digivet.ws.service.repositories.MeetCanceledService;

@RestController
@RequestMapping("/canceled")
@CrossOrigin
public class VetMeetingCanceledController {
	Logger logger = LogManager.getLogger(CommentServiceImpl.class);

	@Autowired
	private MeetCanceledService canceledService;
	@PostMapping("/createRecord")
	public meetingCanceledRest createRecord(@RequestBody MeetingCanceledRequestModel canceledRequestModel) {
		VetMeetingCanceledDto storedByService = new VetMeetingCanceledDto();
		VetMeetingCanceledDto canceledDto = new VetMeetingCanceledDto();
		meetingCanceledRest returnValue = new meetingCanceledRest();
		try {
			BeanUtils.copyProperties(canceledRequestModel, canceledDto);
			storedByService = this.canceledService.createRecord(canceledDto);
			BeanUtils.copyProperties(storedByService, returnValue);
			logger.debug("createRecord controller is invoke");
		}catch(Exception ex) {
			logger.error("createRecord controller İs not invoked");
		}
		return returnValue;
	}
	
	
	
	@PostMapping("/getAllRecord")
	public List<meetingCanceledRest> getAllRecord(@RequestBody MeetingCanceledRequestModel canceledRequestModel) {
		VetMeetingCanceledDto canceledDto = new VetMeetingCanceledDto();
		List<meetingCanceledRest> returnValue = new ArrayList<meetingCanceledRest>();
		try {
			BeanUtils.copyProperties(canceledRequestModel, canceledDto);
			
			List<VetMeetingCanceledDto> storedByService = this.canceledService.findAllMethod(canceledDto);
			ModelMapper modelMapper = new ModelMapper();
			for (VetMeetingCanceledDto data : storedByService) {
				returnValue.add(modelMapper.map(data, meetingCanceledRest.class));
			};
			logger.debug("getAllRecord controller is invoke");
		}catch(Exception ex) {
			logger.error("getAllRecord controller İs not invoked");
		}
		return returnValue;	
	}
	
	@GetMapping("/getAll")
	public List<meetingCanceledRest> getAll(){
		List<meetingCanceledRest> returnValue = new ArrayList<meetingCanceledRest>();
		try {
			List<VetMeetingCanceledDto> storedByService = this.canceledService.findAll();
			ModelMapper modelMapper = new ModelMapper();
			for (VetMeetingCanceledDto data : storedByService) {
				returnValue.add(modelMapper.map(data, meetingCanceledRest.class));
			};
			logger.debug("getAllRecord controller is invoke");
		}catch(Exception ex) {
			logger.error("getAllRecord controller İs not invoked");
		}
		return returnValue;
	}
	
	
	
}
