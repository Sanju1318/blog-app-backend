package com.example.choudhary.EntityController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.choudhary.EntityDto.ApiResponse;
import com.example.choudhary.EntityDto.PostDto;
import com.example.choudhary.EntityException.PostResponse;
import com.example.choudhary.EntityService.FileService;
import com.example.choudhary.EntityService.PostService;
import com.example.choudhary.config.AppConstant;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("apis/posts")
public class PostOfController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDto> postData(@RequestBody PostDto dto, @PathVariable Integer userId, @PathVariable Integer categoryId) {
        PostDto dto2 = this.postService.createPost(dto, userId, categoryId);
        return new ResponseEntity<>(dto2, HttpStatus.CREATED);
    }

    @GetMapping("category/{categoryId}")
    public ResponseEntity<List<PostDto>> getByCategory(@PathVariable Integer categoryId) {
        List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<PostDto>> getByUser(@PathVariable Integer userId) {
        List<PostDto> posts = this.postService.getPostByUser(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required = false) String sortDir) {

        PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @GetMapping("posts/{postId}")
    public ResponseEntity<PostDto> getById(@PathVariable Integer postId) {
        PostDto poDto = this.postService.getByIdPost(postId);
        return new ResponseEntity<>(poDto, HttpStatus.OK);
    }

    @PutMapping("update/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto posts = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @DeleteMapping("posts/delete/{postId}")
    public ApiResponse deletePostById(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new ApiResponse("Deleted Data in post", true);
    }

    // Searching Area
    @GetMapping("posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords) {
        List<PostDto> result = this.postService.searchPost(keywords);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Image upload post
    @PostMapping("post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable Integer postId) throws IOException {
        PostDto postDto = this.postService.getByIdPost(postId);
        String filename = this.fileService.uploadImage(path, image);
        postDto.setImageName(filename);
        PostDto updatedPost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    // Method to serve files
    @GetMapping(value = "post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}
