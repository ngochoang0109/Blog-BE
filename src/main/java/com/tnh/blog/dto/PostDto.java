package com.tnh.blog.dto;

import com.tnh.blog.entity.Post;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PostDto extends Post{
	
	public PostDto(long id, String title, String description, String content) {
		super(id, title, description, content);
	}

}
