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

import com.digivet.ws.Shared.dto.UserDto;
import com.digivet.ws.Shared.dto.UserLoginDto;
import com.digivet.ws.entities.Users;
import com.digivet.ws.model.request.UserDetailsLoginRequestModel;
import com.digivet.ws.model.request.UserDetailsRequestModel;
import com.digivet.ws.model.response.UserRest;
import com.digivet.ws.service.repositories.UserService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

	Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping(value = "/createUsers")
	public UserRest createUsers(@RequestBody UserDetailsRequestModel model) {
		UserRest returnValue = new UserRest();
		UserDto data = new UserDto();
		UserDto storedByUserService = new UserDto();
		try {
			logger.debug("createUser controller is invoked");
			BeanUtils.copyProperties(model, data);
			storedByUserService = this.userService.createUser(data);
			BeanUtils.copyProperties(storedByUserService, returnValue);
		} catch (Exception ex) {
			logger.error("createUser controller is not invoked error message :" +ex.getMessage());
			ex.printStackTrace();
		}

		return returnValue;
	}

	@PostMapping(value = "/login")
	public Boolean findUser(@RequestBody UserDetailsLoginRequestModel loginModel) {
		
		System.out.println(loginModel.getEmail());
		boolean returnValue = true;
		UserLoginDto data = new UserLoginDto();
		
		try {
			logger.debug("findUser controller is invoked");
			BeanUtils.copyProperties(loginModel, data);
			returnValue = this.userService.findUser(data);
		} catch (Exception ex) {
			logger.error("findUser controller is not invoked error message :" +ex.getMessage());
		}

		return returnValue;
	}
	
	
	@PostMapping(value = "/profile")
	public UserRest userProfile(@RequestBody UserDetailsRequestModel detailsRequestModel) {
		UserRest returnValue = new UserRest();
		UserDto userDto = new UserDto();
		UserDto storedByService = new UserDto();
		try {
			BeanUtils.copyProperties(detailsRequestModel, userDto);
			storedByService = this.userService.userProfile(userDto);
			BeanUtils.copyProperties(storedByService,returnValue);
			logger.debug("userProfile controller is invoke");
		}catch(Exception ex) {
			logger.error("userProfile controller is not invoke");
		}
		return returnValue;
	}
	
	@PutMapping("/update")
	public UserRest userUpdate(@RequestBody UserDetailsRequestModel detailsRequestModel) {
		UserRest returnValue = new UserRest();
		UserDto userDto = new UserDto();
		UserDto storedByService = new UserDto();
		try {
			BeanUtils.copyProperties(detailsRequestModel, userDto);
			storedByService = this.userService.updateUser(userDto);
			BeanUtils.copyProperties(storedByService,returnValue);
			logger.debug("userProfile controller is invoke");
		}catch(Exception ex) {
			logger.error("userProfile controller is not invoke");
		}
		return returnValue;
	}
	
	@PostMapping("/delete")
	public UserRest deleteUser(@RequestBody UserDetailsRequestModel detailsRequestModel) {
		UserDto userDto = new UserDto();
		try {
			BeanUtils.copyProperties(detailsRequestModel, userDto);
			this.userService.deleteUser(userDto);
			
			logger.debug("userProfile controller is invoke");
		}catch(Exception ex) {
			logger.error("userProfile controller is not invoke");
		}
		return null;
	}
	@GetMapping("/getAll")
	public List<UserRest> getAllUser() {
		List<UserRest> returnValue = new ArrayList<UserRest>();
		try {
			List<UserDto> storedByService = this.userService.getAllUser();
			ModelMapper mapper = new ModelMapper();
			for (UserDto users : storedByService) {
				returnValue.add(mapper.map(users, UserRest.class));
			}
			logger.debug("getAll controller is invoke");

		}catch (Exception e) {
			logger.error("getAll controller is not invoke");

		}
		return returnValue;
	}
	

}
