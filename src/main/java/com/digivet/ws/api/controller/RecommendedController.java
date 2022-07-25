package com.digivet.ws.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digivet.ws.Shared.dto.RecommendationDto;
import com.digivet.ws.model.request.RecommendationRequestModel;
import com.digivet.ws.model.response.RecommendationRest;
import com.digivet.ws.service.repositories.RecommendationService;

@RestController
@CrossOrigin
@RequestMapping("/recommended")
public class RecommendedController {
	Logger logger = LogManager.getLogger(RecommendedController.class);
	
	@Autowired
	private RecommendationService recommendationService;
	@PostMapping(path = "/getAllRecommendedStatus")
	public List<RecommendationRest> getAllRecommendedStatus(@RequestBody RecommendationRequestModel recommendationRequestModel){
		
		List<RecommendationRest> returnValue = new ArrayList<RecommendationRest>();
		RecommendationDto recommendationDto = new RecommendationDto();
		try {
			BeanUtils.copyProperties(recommendationRequestModel, recommendationDto);
			List<RecommendationDto> storedByService = this.recommendationService.getAlRecommendedStatus(recommendationDto);
			ModelMapper modelMapper = new ModelMapper();
			for (RecommendationDto recoment : storedByService) {
				returnValue.add(modelMapper.map(recoment, RecommendationRest.class));
			}
		logger.info("getAllRecommendedStatus controller is invoked");	
		}catch (Exception e) {
		logger.error("getAllRecommendedStatus controller is invoked Message :"+e.getMessage());
		}
		return returnValue;
	}
	@PostMapping(path = "/createRecommendation")
	public RecommendationRest createRecommendation(@RequestBody RecommendationRequestModel recommendationRequestModel) {
		RecommendationDto recommendationDto = new RecommendationDto();
		RecommendationDto storedByService = new RecommendationDto();
		RecommendationRest returnValue = new RecommendationRest();
		try {
			BeanUtils.copyProperties(recommendationRequestModel, recommendationDto);
			storedByService = this.recommendationService.createRecommendation(recommendationDto);
			BeanUtils.copyProperties(storedByService, returnValue);
			logger.info("createRecommendation controller is invoked");
		}catch (Exception e) {
			logger.error("createRecommendation controller is invoked Message :"+e.getMessage());

		}
		return returnValue;
	}
	
	@PostMapping(path = "/updateRecommendation")
	public RecommendationRest updateRecommendation(@RequestBody RecommendationRequestModel recommendationRequestModel) {
	RecommendationDto recommendationDto = new RecommendationDto();
	RecommendationDto storedByService = new RecommendationDto();
	RecommendationRest returnValue = new RecommendationRest();
	try {
		BeanUtils.copyProperties(recommendationRequestModel, recommendationDto);
		storedByService = this.recommendationService.updateRecommendation(recommendationDto);
		BeanUtils.copyProperties(storedByService, returnValue);
		logger.info("updateRecommendation controller is invoked");

	}catch (Exception e) {
		logger.error("updateRecommendation controller is invoked Message :"+e.getMessage());
	}
	
	return returnValue;	
	}
	
	@PostMapping(path="/delete")
	public RecommendationRest deleteRecommendation(@RequestBody RecommendationRequestModel recommendationRequestModel) {
		RecommendationDto recommendationDto = new RecommendationDto();
		try {
			BeanUtils.copyProperties(recommendationRequestModel, recommendationDto);
			this.recommendationService.deleteRecommendation(recommendationDto);
			logger.info("deleteRecommendation controller is invoked");

		}catch(Exception e) {
			logger.error("deleteRecommendation controller is invoked Message :"+e.getMessage());

		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	


}
