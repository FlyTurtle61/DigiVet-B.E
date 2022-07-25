package com.digivet.ws.service.repositories;

import java.util.List;

import com.digivet.ws.Shared.dto.VetDto;
import com.digivet.ws.Shared.dto.VetLoginDto;

public interface VetServices {

	VetDto createVet(VetDto vet);
	Boolean findVet(VetLoginDto loginDto);
	VetDto vetProfile(VetDto vetDto);
	VetDto updateVet(VetDto vetDto);
	List<VetDto> getAllVet();
	VetDto deletevet(VetDto vetDto);
}
