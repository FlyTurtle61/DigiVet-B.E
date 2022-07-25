package com.digivet.ws.service.repositories;

import java.util.List;
import com.digivet.ws.Shared.dto.CommentDto;


public interface CommentsService {

	List<CommentDto> findComment(CommentDto commentDto);
	CommentDto createComment(CommentDto commentDto);
	CommentDto deleteComment(CommentDto commentDto);
	List<CommentDto> findAllComment();
	
	
}
