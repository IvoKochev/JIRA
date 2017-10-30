package com.jira.repository;

import com.jira.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("issueRepository")
public interface IssueRepository extends JpaRepository<Issue, Long>{
    public Issue findById(int id);
}
