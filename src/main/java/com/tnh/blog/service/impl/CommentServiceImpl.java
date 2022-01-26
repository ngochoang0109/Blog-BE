package com.tnh.blog.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tnh.blog.dto.CommentDto;
import com.tnh.blog.entity.Comment;
import com.tnh.blog.entity.Post;
import com.tnh.blog.exception.BlogAPIException;
import com.tnh.blog.exception.ResourceNotFoundException;
import com.tnh.blog.repository.CommentRepository;
import com.tnh.blog.repository.PostRepository;
import com.tnh.blog.service.CommentService;
import com.tnh.blog.utils.ObjectMapToEntity;

@Service
public class CommentServiceImpl implements CommentService{
	private CommentRepository commentRepository;
	private PostRepository postRepository;

	public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
		super();
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}


	@Override
	public CommentDto addComment(CommentDto commentDto, long postId) {
		Comment comment= ObjectMapToEntity.mapCommentDtoToComment(commentDto);
		Post post= this.postRepository.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("post", "id", postId));
		comment.setPost(post);
		Comment newComment=this.commentRepository.save(comment);
		return ObjectMapToEntity.mapCommentToCommentDto(newComment);
	}
	
	@Override
	public List<CommentDto> getCommentsByPostId(long postId) {
		List<Comment> comments= this.commentRepository.getCommentsByPostId(postId);
		return ObjectMapToEntity.mapCommentsToCommentDtos(comments);
	}
	
	@Override
	public CommentDto getCommentById(long id, long postId) {
		Post post= this.postRepository.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("post", "id", postId));
		
		Comment comment= this.commentRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("comment", "id", id));
		
		if (comment.getPost().getId()!=post.getId()) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment not belong post");
		}
		return ObjectMapToEntity.mapCommentToCommentDto(comment);
	}
	
	@Override
	public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {
		Post post= this.postRepository.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("post", "id", postId));
		
		Comment comment= this.commentRepository.findById(commentId)
				.orElseThrow(()->new ResourceNotFoundException("comment", "id", commentId));
		if (comment.getPost().getId()!=post.getId()) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment not belong post");
		}
		
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());
		comment.setPost(post);
		
		Comment updateComment=this.commentRepository.save(comment);
		return ObjectMapToEntity.mapCommentToCommentDto(updateComment);
	}
	@Override
	public void deleteComment(long postId, long commentId) {
		Post post= this.postRepository.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("post", "id", postId));
		
		Comment comment= this.commentRepository.findById(commentId)
				.orElseThrow(()->new ResourceNotFoundException("comment", "id", commentId));
		if (comment.getPost().getId()!=post.getId()) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment not belong post");
		}
		this.commentRepository.delete(comment);
	}
}
