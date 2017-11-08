package com.jira.contract;

import com.jira.model.Issue;

public interface IIssueService {

	void saveIssue(Issue issue);

	Issue getIssue(int id);

	public boolean isValidEmailAddress(String email);

	public void deleteIssue(int id);

}
