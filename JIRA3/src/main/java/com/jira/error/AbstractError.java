package com.jira.error;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jira.exceptions.AccessDeniedException;
import com.jira.exceptions.ResourceNotFoundException;

public abstract class AbstractError {

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String unauthorized(Model model) {
		model.addAttribute("img", "/images/error/401.jpg");
		model.addAttribute("message", "Access Denied");
		model.addAttribute("errorStatus", "401 Unauthorized ");
		return "/common/error";
	}
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String resourceNotFound(Model model) {
		model.addAttribute("img", "/images/error/404.jpg");
		model.addAttribute("message", "Resource Not Found");
		model.addAttribute("errorStatus", "404 Not Found ");
		return "/common/error";
	}
}
