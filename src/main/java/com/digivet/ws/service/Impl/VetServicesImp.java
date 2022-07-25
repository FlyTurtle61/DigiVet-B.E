package com.digivet.ws.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.digivet.ws.Exceptions.VetServiceException;
import com.digivet.ws.Shared.dto.CommentDto;
import com.digivet.ws.Shared.dto.MeetingDto;
import com.digivet.ws.Shared.dto.VetDto;
import com.digivet.ws.Shared.dto.VetLoginDto;
import com.digivet.ws.entities.Comments;
import com.digivet.ws.entities.Vet;
import com.digivet.ws.repositories.VetRepository;
import com.digivet.ws.service.repositories.VetServices;

@Service
public class VetServicesImp implements VetServices {

	Logger logger = LogManager.getLogger(VetServicesImp.class);

	@Autowired
	private VetRepository repository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public VetDto createVet(VetDto vet) {
		VetDto returnValue = new VetDto();
		Vet vetEntity = new Vet();
		Vet storedByVet = new Vet();
		try {
			logger.info("createVet service is invoke");

			if (repository.findByEmail(vet.getEmail()) != null)
				throw new VetServiceException("this record already exist");

			String jwt = bCryptPasswordEncoder.encode(vet.getPassword());
			vet.setPassword(jwt);

			BeanUtils.copyProperties(vet, vetEntity);
			storedByVet = this.repository.save(vetEntity);
			BeanUtils.copyProperties(storedByVet, returnValue);
		} catch (Exception ex) {
			logger.error("createVet service is not invoke error message:" + ex.getMessage());

		}
		return returnValue;
	}

	@Override
	public Boolean findVet(VetLoginDto loginDto) {
		Boolean returnValue = true;
		Vet vetEntity = new Vet();
		try {
			logger.info("findVet service is invoke");

			BeanUtils.copyProperties(loginDto, vetEntity);
			String email = vetEntity.getEmail();
			String password = vetEntity.getPassword();
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if (encoder.matches(password,this.repository.findByPassword(email)) == true)
			{
				returnValue = true;
			}else {
				returnValue = false;
			}
			
		} catch (Exception ex) {
			logger.error("findVet service is not invoke error message:" + ex.getMessage());
			returnValue = false;
		}
		return returnValue;
	}

	@Override
	public VetDto vetProfile(VetDto vetDto) {
		VetDto returnValue = new VetDto();
		Vet entity = new Vet();		
		Vet storedByData = new Vet();
		try {
			BeanUtils.copyProperties(vetDto, entity);
			String email = entity.getEmail();
			
			
			if (repository.findByEmail(email) == null) throw new VetServiceException("this record could not find");
			storedByData = this.repository.findByVetProfile(email);
			System.out.println(storedByData.getPassword());
			BeanUtils.copyProperties(storedByData,returnValue);
			
			logger.info("vet profile service is invoke");
		}catch (Exception e) {
			logger.error("vet profile service is not invoke error :"+e.getMessage());
		}
		
		return returnValue;
	}

	@Override
	public VetDto updateVet(VetDto vetDto) {
		VetDto returnValue = new VetDto();
		Vet vetEntity = new Vet();
		Vet storedByData = new Vet();
		try {
			BeanUtils.copyProperties(vetDto, vetEntity);
			String Password = this.bCryptPasswordEncoder.encode(vetEntity.getPassword());
			vetEntity.setPassword(Password);
			storedByData = this.repository.save(vetEntity);
			BeanUtils.copyProperties(storedByData, returnValue);
			logger.info("updateVet service is invoke");

		}catch (Exception e) {
		logger.error("UpdateVet service is not invoke");	
		}
		return returnValue;
	}

	@Override
	public List<VetDto> getAllVet() {

		List<Vet> storedByData = new ArrayList<Vet>();
		List<VetDto> returnValue = new ArrayList<VetDto>();
		try {
			storedByData = this.repository.findAll();
			ModelMapper mapper = new ModelMapper();
			for (Vet vets : storedByData) {
				returnValue.add(mapper.map(vets, VetDto.class));
			}
			logger.info("getAllVet service is invoke");

		}catch (Exception e) {
			logger.error("getAllVet service is not invoked");
		}
		return returnValue;
	}

	@Override
	public VetDto deletevet(VetDto vetDto) {

		Vet vetEntity = new Vet();

		try {
			BeanUtils.copyProperties(vetDto, vetEntity);
			int id = vetEntity.getId();
			this.repository.deleteById(id);
			logger.info("deleteMeet service is invoke");

		}catch(Exception ex) {
			logger.error("deleteMeet service is not invoked Error :"+ex.getMessage());

		}
		return null;
	}

}
