package com.smart.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class MyHandlerExceptionResolver {

	@ExceptionHandler(RuntimeException.class)
	public String handleException(RuntimeException re, HttpServletRequest request) {
		System.out.println(re.toString());
		return "forward:error.jsp";
	}
}
