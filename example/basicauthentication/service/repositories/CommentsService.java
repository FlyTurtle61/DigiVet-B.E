package com.springsecurity.basicauthentication.service.repositories;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springsecurity.basicauthentication.Shared.dto.CommentDto;
import com.springsecurity.basicauthentication.entities.Comments;

public interface CommentsService {

	List<CommentDto> findComment(CommentDto commentDto);
	CommentDto createComment(CommentDto commentDto);
	CommentDto deleteComment(int id);
	
	
}
