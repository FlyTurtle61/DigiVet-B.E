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

import com.digivet.ws.Exceptions.UserServiceException;
import com.digivet.ws.Shared.dto.RecommendationDto;
import com.digivet.ws.Shared.dto.UserDto;
import com.digivet.ws.Shared.dto.UserLoginDto;
import com.digivet.ws.entities.Recommendation;
import com.digivet.ws.entities.Users;
import com.digivet.ws.model.response.User;
import com.digivet.ws.repositories.UserRepository;
import com.digivet.ws.service.repositories.UserService;

@Service
public class UserServiceImp implements UserService {

	Logger logger = LogManager.getLogger(UserServiceImp.class);

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) throws Exception {

		UserDto returnValue = new UserDto();
		Users userEntity = new Users();
		Users storedByData = new Users();
		try {
			logger.info("createUser service is invoke");

			String jwt = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(jwt);
			if (userRepository.findByEmail(user.getEmail()) != null)
				throw new UserServiceException("Record already exists");
			BeanUtils.copyProperties(user, userEntity);
			storedByData = this.userRepository.save(userEntity);
			BeanUtils.copyProperties(storedByData, returnValue);
		} catch (Exception ex) {
			logger.error("createUser service is not invoke error message:" + ex.getMessage());
		}

		return returnValue;
	}

	@Override
	public Boolean findUser(UserLoginDto user) {
		boolean returnValue = true;
		Users userEntity = new Users();
		try {
			logger.info("findUser service is invoke");

			BeanUtils.copyProperties(user, userEntity);
			String email = userEntity.getEmail();
			String password = userEntity.getPassword();
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
						
			if (encoder.matches(password,this.userRepository.findByPassword(email)) == true)
			{
				
				returnValue = true;
			}
			
			else {
				returnValue = false;
			}
		} catch (Exception ex) {
			logger.error("findUser service is not invoke error message:" + ex.getMessage());
			returnValue = false;

		}
		return returnValue;
	}

	@Override
	public UserDto userProfile(UserDto userDto) {

		UserDto returnValue = new UserDto();
		Users userEntity = new Users();
		Users storedByData = new Users();
		try {
			BeanUtils.copyProperties(userDto, userEntity);
			String email = userEntity.getEmail();
			storedByData = this.userRepository.findByUserProfile(email);
			BeanUtils.copyProperties(storedByData, returnValue);
			logger.info("userProfile Service is invoke");
		}catch (Exception e) {
			logger.error("UserProfile Service is not invoke");
		}
		return returnValue;
	}

	@Override
	public UserDto updateUser(UserDto userDto) {
		UserDto returnValue = new UserDto();
		Users userEntity = new Users();
		Users storedByData = new Users();
		try {
			BeanUtils.copyProperties(userDto, userEntity);
			String password = userEntity.getPassword();
			String jwt = this.bCryptPasswordEncoder.encode(password);
			userEntity.setPassword(jwt);
			storedByData = this.userRepository.save(userEntity);
			BeanUtils.copyProperties(storedByData, returnValue);
			logger.info("updateUser Service is invoke");
		}catch (Exception e) {
			logger.error("updateUser Service is not invoke");
		}
		return returnValue;
			
	}

	@Override
	public UserDto deleteUser(UserDto userDto) {
		Users users = new Users();
		try {
			BeanUtils.copyProperties(userDto, users);
			int id = users.getId();
			this.userRepository.deleteById(id);
			
			
			logger.info("deleteUser Service is invoke");
		}catch (Exception e) {
			logger.error("deleteUser Service is not invoke");
		}
		return null;
	}

	@Override
	public List<UserDto> getAllUser() {
		List<UserDto> returnValue = new ArrayList<UserDto>();
		try {
			List<Users> storedByData = this.userRepository.findAll();
			ModelMapper mapper = new ModelMapper();
			for (Users recoment : storedByData) {
				returnValue.add(mapper.map(recoment, UserDto.class));
			}
			logger.info("getAllUser Service is invoke");

		}catch(Exception ex) {
			logger.error("getAllUser Service is not invoke");

		}
		return returnValue;
	}
	
	

}
