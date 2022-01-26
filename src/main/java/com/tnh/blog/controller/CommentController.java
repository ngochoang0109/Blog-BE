package com.tnh.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tnh.blog.dto.CommentDto;
import com.tnh.blog.service.CommentService;

import net.bytebuddy.asm.Advice.This;

@RestController
@RequestMapping("/api")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@PostMapping(value = "/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId, 
			@RequestBody CommentDto commentDto){
		return new ResponseEntity<CommentDto>(this.commentService.addComment(commentDto, postId), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/posts/{postId}/comments")
	public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") long postId){
		return this.commentService.getCommentsByPostId(postId);
	}
	
	@GetMapping(value = "/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> getCommentBelongPostId(
			@PathVariable(value = "postId") long postId,
			@PathVariable(value = "commentId") long commentId){
		return new ResponseEntity<CommentDto>(this.commentService.getCommentById(commentId, postId), HttpStatus.OK);
	}
	
	@PutMapping(value = "/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> updateComment(
			@PathVariable(value = "postId") long postId,
			@PathVariable(value = "commentId") long commentId,
			@RequestBody CommentDto commentDto){
		return new ResponseEntity<CommentDto>(this.commentService.updateComment(postId,commentId, commentDto), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/posts/{postId}/comments/{commentId}")
	public ResponseEntity<String> deleteComment(
			@PathVariable(value = "postId") long postId,
			@PathVariable(value = "commentId") long commentId) {
		this.commentService.deleteComment(postId, commentId);
		return new ResponseEntity<String>("Comment deleted", HttpStatus.OK);
	}
}
