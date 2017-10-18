package jira.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jira.contracts.IIssueService;
import jira.exceptions.ResourceNotFoundException;
import jira.models.Issue;

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
		return this.issueService.createIssue(issue, request);
	}

	@RequestMapping(value = "update", method = RequestMethod.PUT)
	public Issue updateIssue(@RequestBody Issue issue) throws ResourceNotFoundException {
		return this.issueService.updateIssue(issue);
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public List<Issue> issueList(HttpServletRequest request) throws ResourceNotFoundException {
		return this.issueService.issueList(request);
	}
}
