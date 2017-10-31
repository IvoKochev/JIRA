package com.jira.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jira.model.Attachment;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public Attachment addAttachment(@RequestBody Attachment attachment, HttpServletRequest request) {
		
	return null;	
	}
}
