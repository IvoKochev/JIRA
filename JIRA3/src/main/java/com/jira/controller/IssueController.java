package com.jira.controller;

import com.jira.contract.IIssueService;
import com.jira.contract.ISprintService;
import com.jira.model.Issue;
import com.jira.model.Sprint;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IssueController {
    @Autowired
    private IIssueService issueService;
    @Autowired
    private ISprintService sprintService;
	
    @RequestMapping(value = "/common/createIssue", method = RequestMethod.GET)
    public ModelAndView getIssue() {
        Issue issue = new Issue();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("issue", issue);
        modelAndView.setViewName("common/createIssue");
        return modelAndView;
    }
    
    @RequestMapping(value = "/createIssue", method = RequestMethod.POST)
    public ModelAndView createIssue(@ModelAttribute Issue issue, HttpServletRequest request) {
        issue.setAsignee_id(4);
        issue.setReporter_id(4);
        Sprint sprint = sprintService.findSprintById(2);
        issue.setSprints_id(sprint);
        issue.setStatus("TO DO");
        issueService.saveIssue(issue);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/common/projectView");
        return modelAndView;
    }
}
