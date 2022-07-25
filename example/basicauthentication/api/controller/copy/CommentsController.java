package com.springsecurity.basicauthentication.api.controller.copy;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springsecurity.basicauthentication.Shared.dto.CommentDto;
import com.springsecurity.basicauthentication.entities.Comments;
import com.springsecurity.basicauthentication.model.request.CommentDetailsRequestModel;
import com.springsecurity.basicauthentication.model.response.CommentRest;
import com.springsecurity.basicauthentication.service.repositories.CommentsService;

@RestController
@RequestMapping("/digivet/comments")
@CrossOrigin
public class CommentsController {

	@Autowired
	private CommentsService service;
	
	@PostMapping("/create")
	public CommentRest createComment(@RequestBody CommentDetailsRequestModel comment)
	{
		CommentDto storedByComment = new CommentDto();
		CommentDto commentDto = new CommentDto();
		CommentRest returnValue = new CommentRest();
		BeanUtils.copyProperties(comment, commentDto);
		storedByComment = this.service.createComment(commentDto);
		BeanUtils.copyProperties(storedByComment, returnValue);
			
		return returnValue;
	}
	
	@PostMapping("/findComment")
	
	public List<CommentRest> findComment(@RequestBody CommentDetailsRequestModel comment)
	{
		CommentDto commentDto = new CommentDto();
		BeanUtils.copyProperties(comment, commentDto);
		List<CommentDto> storedByCommentDtos = new ArrayList<CommentDto>();
		storedByCommentDtos = this.service.findComment(commentDto);
		List<CommentRest> returnValue = new ArrayList<CommentRest>();
		ModelMapper mapper = new ModelMapper();
		for(CommentDto comments:storedByCommentDtos)
        {
            returnValue.add(mapper.map(comments, CommentRest.class));
        }
		return returnValue;
	}
	
	@DeleteMapping("/deleteComment{id}")
	public List<CommentRest> deleteComment(@Param(value="id") int id)
	{
		this.service.deleteComment(id);
		return null;
		
	}
}
