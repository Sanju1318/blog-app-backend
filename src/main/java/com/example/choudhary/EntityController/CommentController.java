package com.example.choudhary.EntityController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.choudhary.EntityDto.ApiResponse;
import com.example.choudhary.EntityDto.CommentDto;
import com.example.choudhary.EntityService.Impl.CommentServiceImpl;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("apis/")
public class CommentController {
	
	@Autowired 
	private CommentServiceImpl commentServiceImpl;
	
	
	@PostMapping("comment/{postId}/comment")
	
	private ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer postId)
	{
		
		CommentDto commentDto2=this.commentServiceImpl.createComment(commentDto, postId);
		
		return new ResponseEntity<CommentDto>(commentDto2,HttpStatus.CREATED);
	}
	
	@DeleteMapping("comment/{commentId}")
	
	private ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId)
	{
		this.commentServiceImpl.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Success",true), HttpStatus.OK);
	}

}
