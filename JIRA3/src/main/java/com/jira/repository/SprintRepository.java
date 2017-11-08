package com.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jira.model.Sprint;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Repository("sprintRepository")
@Transactional
public interface SprintRepository extends JpaRepository<Sprint, Integer> {
	Sprint findById(int id);

	@Modifying
	@Query(value = "DELETE FROM sprints WHERE id =:id", nativeQuery = true)
	void deleteIssue(@Param("id") int id);
}
