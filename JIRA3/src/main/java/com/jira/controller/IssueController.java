package com.jira.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jira.cotract.IIssueService;
import com.jira.exceptions.ResourceNotFoundException;
import com.jira.model.Issue;

@RestController
@RequestMapping("/issue")
public class IssueController {

	IIssueService issueService;

	@Autowired
	public IssueController(IIssueService issueService) {
		this.issueService = issueService;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public Issue createIssue(@RequestBody Issue issue, HttpServletRequest request) {
		Issue issue2 = this.issueService.createIssue(issue, request);
		return issue2;
	}

	@RequestMapping(value = "update", method = RequestMethod.PUT)
	public Issue updateIssue(@RequestBody Issue issue) throws ResourceNotFoundException {
		return this.issueService.updateIssue(issue);
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public List<Issue> getIssueList(HttpServletRequest request) throws ResourceNotFoundException {
		return this.issueService.issueList(request);
	}

	@RequestMapping(value = "/{issue_id}", method = RequestMethod.GET)
	public Issue getIssue(@PathVariable(value = "issue_id") int id, HttpServletRequest request)
			throws ResourceNotFoundException {
		return this.issueService.getIssue(id, request);
	}
}
