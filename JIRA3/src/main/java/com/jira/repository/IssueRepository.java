package com.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jira.model.Issue;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Repository("issueRepository")
@Transactional
public interface IssueRepository extends JpaRepository<Issue, Long> {
	public Issue findById(Integer id);

	@Modifying
	@Query(value = "DELETE FROM issues WHERE id =:id", nativeQuery = true)
	void deleteIssue(@Param("id") int id);

}
