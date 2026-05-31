package com.example.choudhary.EntityService.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.choudhary.EntityApis.Comment;
import com.example.choudhary.EntityApis.Post;
import com.example.choudhary.EntityDto.CommentDto;
import com.example.choudhary.EntityException.ResourceNotFoundException;
import com.example.choudhary.EntityRepo.CommentRepository;
import com.example.choudhary.EntityRepo.PostRepository;
import com.example.choudhary.EntityService.CommentService;
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	 private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post= this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "postId", postId));
		
		Comment comment=this.modelMapper.map(commentDto,Comment.class);
		
		comment.setPost(post);
		
		Comment savecomment=this.commentRepository.save(comment);
		
		return this.modelMapper.map(savecomment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		Comment comment=this.commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "CommentId", commentId));
		
		this.commentRepository.delete(comment);

	}

}
