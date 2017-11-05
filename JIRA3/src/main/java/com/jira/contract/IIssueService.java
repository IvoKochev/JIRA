package com.jira.contract;

import com.jira.exceptions.ResourceNotFoundException;
import com.jira.model.Issue;

public interface IIssueService {

	void saveIssue(Issue issue);

	void updateIssue(Issue issue) throws ResourceNotFoundException;

	Issue getIssue(int id);

	public boolean isValidEmailAddress(String email);

	public void deleteIssue(int id);

}
