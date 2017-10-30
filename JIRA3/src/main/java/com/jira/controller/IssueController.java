package com.jira.controller;

import com.jira.model.Issue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IssueController {

	
    @RequestMapping(value = "/common/createIssue", method = RequestMethod.GET)
    public ModelAndView test() {
        Issue issue = new Issue();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("issue", issue);
        modelAndView.setViewName("common/createIssue");
        return modelAndView;
    }
}
