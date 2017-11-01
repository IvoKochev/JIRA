package com.jira.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.jira.file.FileWriter;

@Controller
public class AttachmentController {

	@PostMapping("/addAttachment")
	public String addAttachment(HttpServletRequest request) throws IOException, ServletException {
		Part attachment = request.getPart("attachment");
		int id = (int) request.getSession().getAttribute("user_id");
		new FileWriter().writeAttachment(attachment, "firstAttachment", id);
		return "redirect:/common/home#!";
	}
}
