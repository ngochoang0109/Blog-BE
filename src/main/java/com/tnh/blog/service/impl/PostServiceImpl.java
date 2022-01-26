package com.tnh.blog.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tnh.blog.dto.PostDto;
import com.tnh.blog.entity.Post;
import com.tnh.blog.exception.ResourceNotFoundException;
import com.tnh.blog.payload.response.PostResponse;
import com.tnh.blog.repository.PostRepository;
import com.tnh.blog.service.PostService;
import com.tnh.blog.utils.ObjectMapToEntity;
import com.tnh.blog.utils.PagingAndSofting;

@Service
@Transactional
public class PostServiceImpl implements PostService{
	private PostRepository postRepository;

	public PostServiceImpl(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}


	@Override
	public PostDto addPost(PostDto postDto) {
		Post post= new Post(postDto.getTitle(), 
				postDto.getDescription(), postDto.getDescription());
		Post newPost= this.postRepository.save(post);
		PostDto postRespone=new PostDto(newPost.getId(), newPost.getTitle(), 
				newPost.getDescription(), newPost.getContent());
		return postRespone;
	}
	
	@Override
	public PostResponse getPosts(int pageNo, int pageSize, String field) {
		Pageable pageable= PagingAndSofting.getPageable(pageNo, pageSize, field);
		Page<Post> pagePost= this.postRepository.findAll(pageable);
		PostResponse postResponse= ObjectMapToEntity.mapPageToPostResponse(pagePost);
		return postResponse;
	}
	
	@Override
	public PostDto getPostById(long id) {
		Post post= this.postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post", "id", id));
		return ObjectMapToEntity.mapPostToPostDto(post);
	}
	
	@Override
	public PostDto updatePost(PostDto postDto, long id) {
		Post post= this.postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post", "id", id));
		post= ObjectMapToEntity.mapPostDtoToPost(postDto, id);
		Post updatePost= this.postRepository.save(post);
		return ObjectMapToEntity.mapPostToPostDto(updatePost);
	}
	
	@Override
	public void deletePost(long id) {
		// TODO Auto-generated method stub
		this.postRepository.delete(this.postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post", "id", id)));
	}
}
