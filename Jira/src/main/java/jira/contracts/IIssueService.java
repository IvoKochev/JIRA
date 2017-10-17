package jira.contracts;

import jira.exceptions.IssueException;
import jira.models.Issue;

public interface IIssueService {

	Issue createIssue(Issue issue);

	Issue updateIssue(Issue issue) throws IssueException;

}
