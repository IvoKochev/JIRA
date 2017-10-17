package jira.contracts;

import java.util.List;

import jira.exceptions.ResourceNotFoundException;
import jira.models.Issue;

public interface IIssueService {

	Issue createIssue(Issue issue);

	Issue updateIssue(Issue issue) throws ResourceNotFoundException;

	List<Issue> issueList() throws ResourceNotFoundException;

}
