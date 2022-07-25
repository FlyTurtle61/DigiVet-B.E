package com.digivet.ws.service.repositories;

import com.digivet.ws.Shared.dto.AdminDto;

public interface AdminService {

	public AdminDto createAdmin(AdminDto adminDto);
	public Boolean findAdmin(AdminDto adminDto);
}
