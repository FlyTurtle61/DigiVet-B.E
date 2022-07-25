package com.digivet.ws.service.repositories;

import java.util.List;

import com.digivet.ws.Shared.dto.UserDto;
import com.digivet.ws.Shared.dto.UserLoginDto;

public interface UserService {

	public UserDto createUser(UserDto user) throws Exception;
	public Boolean findUser(UserLoginDto user);
	public UserDto userProfile(UserDto userDto);
	public UserDto updateUser(UserDto userDto);
	public UserDto deleteUser(UserDto userDto);
	public List<UserDto> getAllUser();
	
}
