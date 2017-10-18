package jira.contracts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jira.exceptions.ResourceNotFoundException;
import jira.models.Issue;

public interface IIssueService {

	Issue createIssue(Issue issue,HttpServletRequest request);

	Issue updateIssue(Issue issue) throws ResourceNotFoundException;

	List<Issue> issueList(HttpServletRequest request) throws ResourceNotFoundException;

}
