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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digivet.ws.Shared.dto.VetDto;
import com.digivet.ws.Shared.dto.VetLoginDto;
import com.digivet.ws.entities.Vet;
import com.digivet.ws.model.request.VetDetailsLoginModel;
import com.digivet.ws.model.request.VetDetailsRequestModel;
import com.digivet.ws.model.response.VetRest;
import com.digivet.ws.service.repositories.VetServices;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*") // this is a microsystems api

@RequestMapping("/vet")
public class VetController {

	Logger logger = LogManager.getLogger(VetController.class);

	@Autowired
	private VetServices vetServices;

	@PostMapping("/create")
	public VetRest createVet(@RequestBody VetDetailsRequestModel model) {
		VetRest returnValue = new VetRest();
		VetDto dto = new VetDto();
		VetDto storedByServices = new VetDto();
		try {
			BeanUtils.copyProperties(model, dto);
			storedByServices = this.vetServices.createVet(dto);
			BeanUtils.copyProperties(storedByServices, returnValue);
			logger.debug("createVet controller is invoke");
		} catch (Exception ex) {
			logger.error("createVet controller is not invoked error message :" +ex.getMessage());
		}

		return returnValue;
	}

	@PostMapping("/login")
	public Boolean findByVet(@RequestBody VetDetailsLoginModel model) {
		Boolean returnValue = false;
		
		VetLoginDto loginDto = new VetLoginDto();
		try {
			logger.debug("findByVet controller is invoke");

			BeanUtils.copyProperties(model, loginDto);
			returnValue = this.vetServices.findVet(loginDto);
			
		} catch (Exception ex) {
			logger.error("findByVet controller is not error message :" +ex.getMessage());

		}
		return returnValue;
	}
	
	@PostMapping("/profile")
	public VetRest vetProfile(@RequestBody VetDetailsRequestModel detailsRequestModel) {
		VetDto storedByService = new VetDto();
		VetDto service = new VetDto();
		VetRest returnValue = new VetRest();
		try {
			BeanUtils.copyProperties(detailsRequestModel, service);
			storedByService = this.vetServices.vetProfile(service);
			BeanUtils.copyProperties(storedByService, returnValue);
			logger.debug("vetProfile controller is invoke");
		}catch(Exception e) {
			logger.error("vetProfile controller is not invoke error : "+e.getMessage());
		}
		return returnValue;
	}
	
	@PutMapping("/update")
	public VetRest vetUpdate(@RequestBody VetDetailsRequestModel detailsRequestModel) {
		
		VetDto storedByService = new VetDto();
		VetDto service = new VetDto();
		VetRest returnValue = new VetRest();
		try {
			BeanUtils.copyProperties(detailsRequestModel, service);
			storedByService = this.vetServices.updateVet(service);
			BeanUtils.copyProperties(storedByService, returnValue);
			logger.debug("vetUpdate controller is invoke");
		}catch (Exception e) {
			logger.error("vetUpdate controller is not invoke");
		}
		return returnValue;
	}
	@GetMapping("/getAll")
	public List<VetRest> getAllVet() {
		
		List<VetRest> returnValue = new ArrayList<VetRest>();
		List<VetDto> storedByService = new ArrayList<VetDto>();
		try {
			storedByService = this.vetServices.getAllVet();
			ModelMapper mapper = new ModelMapper();
			for (VetDto vets : storedByService) {
				returnValue.add(mapper.map(vets, VetRest.class));
			}
			logger.debug("getAllVet controller is invoke");
		}catch (Exception e) {
			logger.error("getAllVet controller is not invoked");
		}
		return returnValue;
	}
	
	@PostMapping("/delete")
	public VetRest deleteVet(@RequestBody VetDetailsRequestModel vetDetailsRequestModel) {
		VetDto vetDto = new VetDto();
		try {
			BeanUtils.copyProperties(vetDetailsRequestModel, vetDto);
			this.vetServices.deletevet(vetDto);
			logger.debug("delete controller is invoke ");

		}catch(Exception ex) {
			logger.error("delete controller is not invoke");

		}
		return null;
	}
}
