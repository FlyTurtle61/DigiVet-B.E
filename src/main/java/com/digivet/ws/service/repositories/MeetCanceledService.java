package com.digivet.ws.service.repositories;

import java.util.List;

import com.digivet.ws.Shared.dto.VetMeetingCanceledDto;
import com.digivet.ws.model.request.MeetingCanceledRequestModel;
import com.digivet.ws.model.request.MeetingDetailsRequestModel;

public interface MeetCanceledService {

	public VetMeetingCanceledDto createRecord(VetMeetingCanceledDto canceledDto);
	public List<VetMeetingCanceledDto> findAllMethod(VetMeetingCanceledDto canceledDto);
	public List<VetMeetingCanceledDto> findAll();
}
