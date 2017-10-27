package com.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jira.model.UserHasProject;

@Repository("userHasProjectRepository")
public interface UserHasProjectRepository extends JpaRepository<UserHasProject, Integer> {
}
