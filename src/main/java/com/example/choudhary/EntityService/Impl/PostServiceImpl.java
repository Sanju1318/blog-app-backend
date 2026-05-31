package com.example.choudhary.EntityService.Impl;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.choudhary.EntityApis.Category;
import com.example.choudhary.EntityApis.Post;
import com.example.choudhary.EntityApis.User;
import com.example.choudhary.EntityDto.PostDto;
import com.example.choudhary.EntityException.PostResponse;
import com.example.choudhary.EntityException.ResourceNotFoundException;
import com.example.choudhary.EntityRepo.CategoryRepository;
import com.example.choudhary.EntityRepo.PostRepository;
import com.example.choudhary.EntityRepo.UserRepo;
import com.example.choudhary.EntityService.PostService;
@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		// TODO Auto-generated method stub
		
		User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "user id", userId));
		Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->
		new ResourceNotFoundException("Category", "Category id", categoryId));
		
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setImageName("default");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost=this.postRepository.save(post);
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		
		Post posts=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id" ,postId));
		
		posts.setTitle(postDto.getTitle());
		posts.setContent(postDto.getContent());
		posts.setImageName(postDto.getImageName());
		Post updateposts=this.postRepository.save(posts);
		return this.modelMapper.map(updateposts,PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post posts=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post id", postId));
		this.postRepository.delete(posts);
		

	}

	@Override
	public PostResponse getAllPost( Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		// TODO Auto-generated method stub
		
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		Pageable p=PageRequest.of(pageNumber, pageSize,sort );
		Page<Post> pagePost=this.postRepository.findAll(p);
		List<Post> allpost=pagePost.getContent();
		List<PostDto> postDtos=allpost.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());;
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getByIdPost(Integer postId) {
		// TODO Auto-generated method stub
	Post posts=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post id", postId));
		return this.modelMapper.map(posts, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		
		Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category id", categoryId));
		List<Post> posts=this.postRepository.findByCategory(category);
		
		List<PostDto> postDto=posts.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "user :id",userId));
		
		List<Post> posts=this.postRepository.findByUser(user);
		List<PostDto> newPost=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return newPost;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		// TODO Auto-generated method stub
		List<Post> posts=this.postRepository.searchByTitle("%"+keyword+"%");
		 List<PostDto>postdto=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postdto;
	}

}
