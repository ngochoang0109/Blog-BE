package com.tnh.blog.service;

import java.util.List;

import com.tnh.blog.dto.CommentDto;

public interface CommentService {
	CommentDto addComment(CommentDto commentDto, long postId);
	List<CommentDto> getCommentsByPostId(long postId);
	CommentDto getCommentById(long id, long postId);
	CommentDto updateComment(long postId ,long commentId, CommentDto commentDto);
	void deleteComment(long postId ,long commentId);
}