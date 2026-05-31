package com.example.choudhary.EntityService;

import com.example.choudhary.EntityDto.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto,Integer postId);
	
	void deleteComment(Integer commentId);

}
