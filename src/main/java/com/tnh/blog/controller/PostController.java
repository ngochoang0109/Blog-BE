package com.tnh.blog.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tnh.blog.constrant.PagingAndSortingConstrant;
import com.tnh.blog.dto.PostDto;
import com.tnh.blog.payload.response.PostResponse;
import com.tnh.blog.service.PostService;

import net.bytebuddy.asm.Advice.This;

@RestController
@RequestMapping(value = "/api/posts")
public class PostController {
	
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
		PostDto postRespone= this.postService.addPost(postDto);
		return new ResponseEntity<PostDto>(postRespone, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<PostResponse> getPosts(@RequestParam(value = "pageNo", defaultValue = PagingAndSortingConstrant.PAGE_NO, required = false) int pageNo, 
			@RequestParam(value = "pageSize", defaultValue = PagingAndSortingConstrant.PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sort", defaultValue = PagingAndSortingConstrant.SORT, required = false) String field){
		return new ResponseEntity<PostResponse>(this.postService.getPosts(pageNo, pageSize, field), HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostDetail(@PathVariable long id) {
		PostDto postDto= this.postService.getPostById(id);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable long id) {
		return new ResponseEntity<PostDto>(this.postService.updatePost(postDto, id),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable long id) {
		this.postService.deletePost(id);
		return new ResponseEntity<String>("Post deleted", HttpStatus.OK);
	}
}
