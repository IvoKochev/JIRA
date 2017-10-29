package com.jira.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jira.model.Sprint;


@Repository("sprintRepository")
public interface SprintRepository extends JpaRepository<Sprint, Integer>{
	Sprint findById(int id);
}
