package com.jira.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jira.model.UserHasProject;

@Repository("userHasProjectRepository")
@Transactional
public interface UserHasProjectRepository extends JpaRepository<UserHasProject, Integer> {

	@Modifying
	@Query(value = "DELETE FROM users_projects WHERE project_id =:id", nativeQuery = true)
	public void deleteProject(@Param("id") int id);

	@Modifying
	@Query(value = "DELETE FROM users_projects WHERE project_id =:projectId AND user_id =:userId", nativeQuery = true)
	public void removeUserFromProject(@Param("userId") int userId, @Param("projectId") int projectId);
}
