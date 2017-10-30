package com.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jira.model.Issue;

@Repository("issueRepository")
public interface IssueRepository extends JpaRepository<Issue, Long> {
	public Issue findById(Integer id);
}
