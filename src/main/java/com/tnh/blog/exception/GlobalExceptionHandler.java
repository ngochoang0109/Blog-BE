package com.tnh.blog.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.tnh.blog.payload.response.ErrorDetail;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetail> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException,
																		WebRequest webRequest){
		ErrorDetail errorDetail= new ErrorDetail(new Date(),resourceNotFoundException.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(BlogAPIException.class)
	public ResponseEntity<ErrorDetail> handleBlogAPIException(BlogAPIException blogAPIException,
																		WebRequest webRequest){
		ErrorDetail errorDetail= new ErrorDetail(new Date(),blogAPIException.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.NOT_FOUND);
	}
	//global exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetail> handleGlobalException(Exception exception,
																		WebRequest webRequest){
		ErrorDetail errorDetail= new ErrorDetail(new Date(),exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<ErrorDetail>(errorDetail, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleGlobalException(MethodArgumentNotValidException ex,
																		WebRequest webRequest){
		Map<String, String> errors= new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String key= ((FieldError)error).getField();
			String value=error.getDefaultMessage();
			errors.put(key, value);
		});
		return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
	}
}
