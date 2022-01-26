package com.tnh.blog.service;

import java.util.List;

import com.tnh.blog.dto.PostDto;
import com.tnh.blog.entity.Post;
import com.tnh.blog.payload.response.PostResponse;

public interface PostService {
	PostDto addPost(PostDto postDto);
	PostResponse getPosts(int pageNo, int pageSize, String field);
	PostDto getPostById(long id);
	PostDto updatePost(PostDto postDto, long id);
	void deletePost(long id);
}
