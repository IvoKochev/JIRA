package com.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jira.model.UserHasProject;

@Repository("userHasProjectRepository")
public interface UserHasProjectRepository extends JpaRepository<UserHasProject, Integer> {
	public static final String FIND_PROJECTS = "SELECT COUNT(b.user_id),b.project_id FROM users_projects b WHERE b.user_id=:user_id and b.project_id=:project_id";

	@Query(value = FIND_PROJECTS, nativeQuery = true)
	Integer findUserHasProject(@Param("user_id") int user_id, @Param("project_id") int project_id);

}
