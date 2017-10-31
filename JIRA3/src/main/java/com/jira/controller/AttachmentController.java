package com.jira.controller;

import com.jira.file.FileWriter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jira.model.Attachment;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AttachmentController {
	
	@RequestMapping(value = "/addAttachment", method = RequestMethod.POST)
        public ModelAndView addAttachment(HttpServletRequest request) throws IOException, ServletException {
            Part attachment = request.getPart("attachment");
            int id = (int) request.getSession().getAttribute("user_id");
            new FileWriter().writeAttachment(attachment, "firstAttachment", id);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("common/issueView");
            return modelAndView;
        }
}
