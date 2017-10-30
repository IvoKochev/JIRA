package com.jira.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.jira.exceptions.ResourceNotFoundException;
import com.jira.model.Issue;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IssueController {

	
    @RequestMapping(value = "/common/createIssue", method = RequestMethod.GET)
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("common/createIssue");
        return modelAndView;
    }
}
