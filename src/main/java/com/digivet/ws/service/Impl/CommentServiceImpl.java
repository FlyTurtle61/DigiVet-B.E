package com.digivet.ws.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digivet.ws.Shared.dto.CommentDto;
import com.digivet.ws.api.controller.CommentsController;
import com.digivet.ws.entities.Comments;
import com.digivet.ws.repositories.CommentRepository;
import com.digivet.ws.service.repositories.CommentsService;

@Service
public class CommentServiceImpl implements CommentsService {
	Logger logger = LogManager.getLogger(CommentServiceImpl.class);

	@Autowired
	private CommentRepository commentRepository;

	public CommentServiceImpl(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	@Override
	public List<CommentDto> findComment(CommentDto commentDto) {
		String email = commentDto.getVetEmail();
		List<CommentDto> returnValue = new ArrayList<CommentDto>();
		ModelMapper mapper = new ModelMapper();
		try {
			List<Comments> storedByComments = this.commentRepository.findByVetEmail(email);

			for (Comments comments : storedByComments) {
				returnValue.add(mapper.map(comments, CommentDto.class));
			}

			logger.info("findComment service is invoke");
		} catch (Exception ex) {

			logger.error("findComment service is not invoke error message:" + ex.getMessage());

		}
		return returnValue;
	}

	@Override
	public CommentDto createComment(CommentDto commentDto) {
		Date date = new Date(System.currentTimeMillis());
		CommentDto returnValue = new CommentDto();
		Comments commentsEntity = new Comments();
		Comments storedByComments = new Comments();

		try {
			logger.info("createComment service is invoke");

			BeanUtils.copyProperties(commentDto, commentsEntity);
			commentsEntity.setDate(date);
			storedByComments = this.commentRepository.save(commentsEntity);
			BeanUtils.copyProperties(storedByComments, returnValue);
		} catch (Exception ex) {
			logger.error("createComment service is not invoke error message:" + ex.getMessage());

		}
		return returnValue;
	}

	@Override
	public CommentDto deleteComment(CommentDto commentDto) {
		Comments commentEntity = new Comments();
		try {
			BeanUtils.copyProperties(commentDto, commentEntity);
			int id = commentEntity.getId();
			logger.info("deleteComment service is invoke");
			this.commentRepository.deleteById(id);
		} catch (Exception ex) {
			logger.error("deleteComment service is not invoke error message:" + ex.getMessage());

		}
		return null;
	}

	@Override
	public List<CommentDto> findAllComment() {
		
		List<CommentDto> returnValue = new ArrayList<CommentDto>();
		ModelMapper mapper = new ModelMapper();
		try {
			List<Comments> storedByData = this.commentRepository.findAll();
			for (Comments comments : storedByData) {
				returnValue.add(mapper.map(comments, CommentDto.class));
				logger.info("findAllComment service is invoke");

			}
		}catch(Exception ex) {
			logger.error("findAllComment service is not invoke error message:" + ex.getMessage());

		}
		return returnValue;
	}

}
