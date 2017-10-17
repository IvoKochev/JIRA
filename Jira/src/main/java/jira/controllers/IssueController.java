package jira.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jira.contracts.IIssueService;
import jira.exceptions.IssueException;
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
	public Issue createIssue(@RequestBody Issue issue) {
		return this.issueService.createIssue(issue);
	}

	@RequestMapping(value = "update", method = RequestMethod.PUT)
	public Issue updateIssue(@RequestBody Issue issue) throws IssueException {
		return this.issueService.updateIssue(issue);
	}

}
