package com.jira.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jira.contract.IIssueService;
import com.jira.contract.ISprintService;
import com.jira.model.Issue;
import com.jira.model.Sprint;
import com.jira.repository.IssueRepository;

@RestController
public class IssueController {
	@Autowired
	private IIssueService issueService;
	@Autowired
	private ISprintService sprintService;
	@Autowired
	IssueRepository issueRepository;

	@RequestMapping(value = "/common/createIssue", method = RequestMethod.GET)
	public ModelAndView issueCreateTemplate() {
		Issue issue = new Issue();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("issue", issue);
		modelAndView.setViewName("common/createIssue");
		return modelAndView;
	}

	@RequestMapping(value = "/createIssue", method = RequestMethod.POST)
	public ModelAndView createIssue(@ModelAttribute Issue issue, HttpServletRequest request) {
		int user_id = (int) request.getSession().getAttribute("user_id");
		issue.setAsignee_id(user_id);
		issue.setReporter_id(user_id);
		int id = Integer.parseInt(request.getParameter("sprintId"));
		Sprint sprint = sprintService.findSprintById(id);
		issue.setSprint(sprint);
		issue.setStatus("TO DO");
		issueService.saveIssue(issue);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/common/home#!/sprintView/" + id);
		return modelAndView;
	}

	@RequestMapping(value = "/common/issueView", method = RequestMethod.GET)
	public ModelAndView getIssueViewTemplate() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("common/issueView");
		return modelAndView;
	}

	@RequestMapping(value = "/common/issueView/{id}", method = RequestMethod.GET)
	public Issue getIssue(@PathVariable(value = "id") int id) {
		System.err.println(id);
		Issue issue = issueRepository.findById(id);
		return issue;
	}
}
