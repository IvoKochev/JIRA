package jira.contracts;

import java.util.List;

import jira.exceptions.IssueException;
import jira.models.Issue;

public interface IIssueService {

	Issue createIssue(Issue issue);

	Issue updateIssue(Issue issue) throws IssueException;

	List<Issue> issueList() throws IssueException;

}
