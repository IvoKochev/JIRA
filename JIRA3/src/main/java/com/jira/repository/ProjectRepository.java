package com.jira.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jira.model.Project;

@Repository("projectRepository")
@Transactional
public interface ProjectRepository extends JpaRepository<Project, Integer> {
	Project findById(int id);

	@Modifying
	@Query(value = "DELETE FROM projects WHERE id =:id",nativeQuery = true)
	void deleteProject(@Param("id") int id);
}
