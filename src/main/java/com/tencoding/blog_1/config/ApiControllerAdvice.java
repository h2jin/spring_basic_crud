package com.tencoding.blog_1.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //?
@ControllerAdvice //? 
public class ApiControllerAdvice {

	@ExceptionHandler(value = Exception.class)
	public String 모든오류(Exception e) {
		System.out.println("오류 메세지" +e.getMessage());
		return e.getMessage();
	}
}
