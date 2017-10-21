package com.jira.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jira.model.Project;

@Repository("projectRepository")
public interface ProjectRepository extends JpaRepository<Project, Long>{
        List<Project> findByowner(String owner);
}
