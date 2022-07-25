package com.digivet.ws.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.digivet.ws.Shared.dto.MeetingDto;
import com.digivet.ws.entities.Meeting;
import com.digivet.ws.model.request.MeetingDetailsRequestModel;
import com.digivet.ws.model.response.MeetRest;
import com.digivet.ws.model.response.VetRest;
import com.digivet.ws.service.repositories.MeetService;

@RestController
@CrossOrigin
@RequestMapping(value = "/digivet/meeting")
public class MeetingController {

	Logger logger = LogManager.getLogger(MeetingController.class);
	@Autowired
	private MeetService meetService;

	public MeetingController(MeetService meetService) {
		this.meetService = meetService;
	}

	@PostMapping(value = "/createMeeting")
	public MeetRest createMeetController(@RequestBody MeetingDetailsRequestModel model) {
		MeetingDto storedByMeeting = new MeetingDto();
		MeetingDto meetingDto = new MeetingDto();
		MeetRest returnValue = new MeetRest();
		try {
			BeanUtils.copyProperties(model, meetingDto);
			storedByMeeting = this.meetService.createMeet(meetingDto);
			BeanUtils.copyProperties(storedByMeeting, returnValue);
			logger.debug("createMeetController is invoke");
		} catch (Exception ex) {
			logger.error("createMeetController is not invoked error message :" +ex.getMessage());
		}

		return returnValue;
	}

	@PostMapping(value = "/getAllVetMeet")
	public List<MeetRest> getAllVetMeet(@RequestBody MeetingDetailsRequestModel model) {
		MeetingDto meetingDto = new MeetingDto();
		List<MeetingDto> storedByMeeting = new ArrayList<MeetingDto>();
		List<MeetRest> returnValue = new ArrayList<MeetRest>();
		ModelMapper mapper = new ModelMapper();

		try {
			BeanUtils.copyProperties(model, meetingDto);
			storedByMeeting = this.meetService.getAllVetMeet(meetingDto);
			for (MeetingDto meet : storedByMeeting) {
				returnValue.add(mapper.map(meet, MeetRest.class));
			}
			logger.debug("getAllVetMeet Controller is invoke");

		} catch (Exception ex) {
			logger.error("getAllVetMeet Controller is not invoked error message :" +ex.getMessage());
		}
		return returnValue;
	}

	@PostMapping(value = "/getAllUserMeet")
	public List<MeetRest> getAllUserMeet(@RequestBody MeetingDetailsRequestModel model) {
		MeetingDto meetingDto = new MeetingDto();
		ModelMapper mapper = new ModelMapper();
		List<MeetingDto> storedByMeeting = new ArrayList<MeetingDto>();
		List<MeetRest> returnValue = new ArrayList<MeetRest>();

		try {
			BeanUtils.copyProperties(model, meetingDto);
			storedByMeeting = this.meetService.getAllUserMeet(meetingDto);
			for (MeetingDto meet : storedByMeeting) {
				returnValue.add(mapper.map(meet, MeetRest.class));
			}
			logger.debug("getAllUserMeet is invoke");

		} catch (Exception ex) {
			logger.error("getAllUserMeet is not invoked error message :" +ex.getMessage());
		}
		return returnValue;
	}

	@PostMapping(value = "/deleteMeet")
	public MeetRest deleteMeet(@RequestBody MeetingDetailsRequestModel requestModel) {
		MeetRest returnValue = new MeetRest();
		MeetingDto storedByService = new MeetingDto();
		MeetingDto meetDto = new MeetingDto();
		
		try {
			BeanUtils.copyProperties(requestModel, meetDto);
			storedByService = this.meetService.deleteMeeting(meetDto);
			BeanUtils.copyProperties(storedByService, returnValue);
			logger.debug("deleteMeet is invoke");

			
		} catch (Exception ex) {
			logger.error("deleteMeet is not invoked error message :" +ex.getMessage());
		}
		return null;
	}

	@PutMapping(value = "/updateMeet")
	public MeetRest updateMeeting(@RequestBody MeetingDetailsRequestModel meetingDetailsRequestModel) {
		MeetingDto storedByMeetUpdate = new MeetingDto();
		MeetRest returnValue = new MeetRest();
		MeetingDto dto = new MeetingDto();
		try {
			BeanUtils.copyProperties(meetingDetailsRequestModel, dto);
			storedByMeetUpdate = this.meetService.updateMeeting(dto);
			BeanUtils.copyProperties(storedByMeetUpdate, returnValue);
			logger.debug("updateMeeting controller is invoke");

		} catch (Exception ex) {
			logger.error("updateMeeting controller is not invoked error message :" +ex.getMessage());
		}

		return returnValue;
	}
	
	
	@GetMapping("/findAll")
	public List<MeetRest> getAllVet(){
		List<MeetRest> returnValue = new ArrayList<MeetRest>();
		
		try {
			List<MeetingDto> storedByService = this.meetService.getAllmeet();
			ModelMapper modelMapper = new ModelMapper();

			for (MeetingDto meet : storedByService) {
				returnValue.add(modelMapper.map(meet, MeetRest.class));
			}
			logger.debug("getAllVet controller service is invoke");
		}catch(Exception ex) {
			logger.error("getAll Service controller is not invoked error :"+ex.getMessage());
		}
		return returnValue;
	}
	

}
