package com.digivet.ws.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digivet.ws.Shared.dto.CommentDto;
import com.digivet.ws.entities.Comments;
import com.digivet.ws.model.request.CommentDetailsRequestModel;
import com.digivet.ws.model.response.CommentRest;
import com.digivet.ws.service.repositories.CommentsService;

@RestController
@RequestMapping("/digivet/comments")
@CrossOrigin
public class CommentsController {

	Logger logger = LogManager.getLogger(CommentsController.class);
	@Autowired
	private CommentsService service;

	@PostMapping("/create")
	public CommentRest createComment(@RequestBody CommentDetailsRequestModel comment) {
		CommentDto storedByComment = new CommentDto();
		CommentDto commentDto = new CommentDto();
		CommentRest returnValue = new CommentRest();
		try {
			BeanUtils.copyProperties(comment, commentDto);
			storedByComment = this.service.createComment(commentDto);
			BeanUtils.copyProperties(storedByComment, returnValue);
			logger.debug("create comment controller is invoked");
		} catch (Exception ex) {
			logger.error("create comment controller is not invoked error message :" + ex.getMessage());
		}

		return returnValue;
	}

	@PostMapping("/findComment")

	public List<CommentRest> findComment(@RequestBody CommentDetailsRequestModel comment) {
		CommentDto commentDto = new CommentDto();
		List<CommentDto> storedByCommentDtos = new ArrayList<CommentDto>();
		ModelMapper mapper = new ModelMapper();
		List<CommentRest> returnValue = new ArrayList<CommentRest>();
		try {

			logger.info("findComment controller is invoked");
			BeanUtils.copyProperties(comment, commentDto);
			storedByCommentDtos = this.service.findComment(commentDto);
			for (CommentDto comments : storedByCommentDtos) {
				returnValue.add(mapper.map(comments, CommentRest.class));
			}
		} catch (Exception ex) {
			logger.error("findComment controller is not invoked error message :" + ex.getMessage());
		}
		return returnValue;
	}

	@PostMapping("/deleteComment")
	public CommentRest deleteComment(@RequestBody CommentDetailsRequestModel commentDetailsRequestModel) {
		CommentDto commentDto = new CommentDto();
		
		try {
			BeanUtils.copyProperties(commentDetailsRequestModel, commentDto);
			this.service.deleteComment(commentDto);
			logger.debug("delete controller is invoke");

		} catch (Exception ex) {

			logger.error("delete controller is not invoked error message :" + ex.getMessage());

		}
		return null;

	}
	
	@GetMapping("/findAll")
	public List<CommentRest> findAllComment(){
		List<CommentRest> returnValue = new ArrayList<CommentRest>();
		try {
			List<CommentDto> storedByService = this.service.findAllComment();
			ModelMapper mapper = new ModelMapper();
			for (CommentDto comments : storedByService) {
				returnValue.add(mapper.map(comments, CommentRest.class));
				logger.info("findAllComment controller is invoke");

			}
		}catch(Exception ex) {
			logger.error("findAllComment controller is not invoked error message :" + ex.getMessage());

		}
		return returnValue;
	}
}
