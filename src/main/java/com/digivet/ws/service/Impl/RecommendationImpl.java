package com.digivet.ws.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.digivet.ws.Shared.dto.RecommendationDto;
import com.digivet.ws.config.MailSenderConfig;
import com.digivet.ws.entities.Recommendation;
import com.digivet.ws.repositories.RecomendationRepository;
import com.digivet.ws.service.repositories.RecommendationService;

@Service
public class RecommendationImpl implements RecommendationService {

	Logger logger = LogManager.getLogger(RecommendationImpl.class);
	
	@Autowired
	private RecomendationRepository recomendationRepository;

	@Autowired
	private MailSenderConfig config;
	@Override
	public List<RecommendationDto> getAlRecommendedStatus(RecommendationDto recommendationDto) {

		List<RecommendationDto> returnValue = new ArrayList<RecommendationDto>();
		Recommendation recommendationEntity = new Recommendation();
		try {
			BeanUtils.copyProperties(recommendationDto, recommendationEntity);
			String status = recommendationEntity.getStatus();
			List<Recommendation> storedByData = this.recomendationRepository.getAllStatus(status);
			ModelMapper mapper = new ModelMapper();
			for (Recommendation recoment : storedByData) {
				returnValue.add(mapper.map(recoment, RecommendationDto.class));
			}
			logger.debug("getAlRecommendedStatus is invoked");
		}catch (Exception e) {
			logger.error("getAlRecommendedStatus is not invoked Message :"+e.getMessage());
		}
				
		return returnValue;
	}

	@Override
	public RecommendationDto createRecommendation(RecommendationDto recommendationDto) {
		RecommendationDto returnValue = new RecommendationDto();
		Recommendation recommendationEntity = new Recommendation();
		Recommendation storedByData = new Recommendation();
		try {
			BeanUtils.copyProperties(recommendationDto, recommendationEntity);
			storedByData = this.recomendationRepository.save(recommendationEntity);
			BeanUtils.copyProperties(storedByData, returnValue);
			this.config.RecommendedCreateSendMail(returnValue);
			logger.debug("createRecommendation Service is invoke");
		}catch(Exception ex) {
			logger.error("createRecommendation service is not invoke Message :"+ex.getMessage());
		}
		return returnValue;
	}

	@Override
	public RecommendationDto updateRecommendation(RecommendationDto recommendationDto) {
		Recommendation recommendationEntity = new Recommendation();
		Recommendation storedByData = new Recommendation();
		RecommendationDto returnValue = new RecommendationDto();
		try {
			
			BeanUtils.copyProperties(recommendationDto, recommendationEntity);
			storedByData = this.recomendationRepository.save(recommendationEntity);
			BeanUtils.copyProperties(storedByData, returnValue);
			if(recommendationDto.getStatus().equals("INPROGRESS")) {
				this.config.RecommendationInProgres(returnValue);
			}else if(recommendationDto.getStatus().equals("COMPLETE")) {
				this.config.RecommendationComplete(returnValue);
			}
			logger.debug("updateRecommendation service is invoked");
		}catch(Exception ex) {
			logger.error("updateRecommendation service is not invoke Message :"+ex.getMessage());
		}
		return returnValue;
	}

	@Override
	public RecommendationDto deleteRecommendation(RecommendationDto recommendationDto) {
		Recommendation recommendationEntity = new Recommendation();
		try {
			BeanUtils.copyProperties(recommendationDto, recommendationEntity);
			this.recomendationRepository.deleteById(recommendationEntity.getId());
			logger.debug("deleteRecommendation service is  invoke");
			
		} catch (Exception e) {
			logger.debug("deleteRecommendation service is not invoked Message :"+e.getMessage());
		}
		return null;
	}

}
