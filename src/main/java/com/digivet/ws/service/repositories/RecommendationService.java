package com.digivet.ws.service.repositories;

import java.util.List;

import com.digivet.ws.Shared.dto.RecommendationDto;

public interface RecommendationService {

	public List<RecommendationDto> getAlRecommendedStatus(RecommendationDto recommendationDto);
	public RecommendationDto createRecommendation(RecommendationDto recommendationDto);
	public RecommendationDto updateRecommendation(RecommendationDto recommendationDto);
	public RecommendationDto deleteRecommendation(RecommendationDto recommendationDto);
	
}
