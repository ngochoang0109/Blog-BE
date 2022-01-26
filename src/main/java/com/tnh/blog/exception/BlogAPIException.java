package com.tnh.blog.exception;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.StringIdGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BlogAPIException extends RuntimeException{
	private HttpStatus status;
	private String message;
}
