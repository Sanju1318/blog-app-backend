package com.example.choudhary.EntityService;

import java.util.List;

import com.example.choudhary.EntityDto.PostDto;
import com.example.choudhary.EntityException.PostResponse;

public interface PostService {
	
	// Create
	
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto,Integer userId);
	
	//delete
	
	void deletePost(Integer postId);
	
	//get All post
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	//get single by Id
	
	PostDto getByIdPost(Integer postId);
	
	//get all posts by category
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//get all posts by user
	
	List<PostDto> getPostByUser(Integer userId);
	
	//search posts
	List<PostDto> searchPost(String keyword);


}
