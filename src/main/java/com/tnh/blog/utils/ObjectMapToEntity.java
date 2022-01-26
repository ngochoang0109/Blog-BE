package com.tnh.blog.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.tnh.blog.dto.CommentDto;
import com.tnh.blog.dto.PostDto;
import com.tnh.blog.entity.Comment;
import com.tnh.blog.entity.Post;
import com.tnh.blog.payload.response.PostResponse;

public class ObjectMapToEntity {
	public static PostDto mapPostToPostDto(Post post) {
		PostDto postDto= new PostDto();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setDescription(post.getDescription());
		postDto.setContent(post.getContent());
		postDto.setComments(post.getComments());
		return postDto;
	}
	
	public static List<PostDto> mapPostsToPostDtos(List<Post> posts){
		List<PostDto> postDtos= new ArrayList<PostDto>();
		for (Post post : posts) {
			PostDto postDto= mapPostToPostDto(post);
			postDtos.add(postDto);
		}
		return postDtos;
	}
	
	
	public static Post mapPostDtoToPost(PostDto postDto, long id) {
		Post post= new Post(id, postDto.getTitle(), 
							postDto.getDescription(), postDto.getContent());
		return post;
	}
	
	
	public static PostResponse mapPageToPostResponse(Page<Post> pagePost) {
		PostResponse postResponse= new PostResponse();
		postResponse.setContent(mapPostsToPostDtos(pagePost.getContent()));
		postResponse.setPageNo(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setLast(pagePost.isLast());
		postResponse.setFirst(pagePost.isFirst());
		return postResponse;
	}
	
	public static Comment mapCommentDtoToComment(CommentDto commentDto) {
		Comment comment= new Comment(commentDto.getName(), commentDto.getEmail(), commentDto.getBody());
		return comment;
	}
	
	public static CommentDto mapCommentToCommentDto(Comment comment) {
		CommentDto commentDto= new CommentDto();
		commentDto.setId(comment.getId());
		commentDto.setName(comment.getName());
		commentDto.setEmail(comment.getEmail());
		commentDto.setBody(comment.getBody());
		return commentDto;
	}
	
	
	public static List<CommentDto> mapCommentsToCommentDtos(List<Comment> comments) {
		List<CommentDto> commentDtos= comments.stream()
				.map(comment->ObjectMapToEntity.mapCommentToCommentDto(comment)).collect(Collectors.toList());
		return commentDtos;
	}
}
