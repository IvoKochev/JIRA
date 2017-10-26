package com.jira.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jira.model.Sprint;

@Repository("sprintRepository")
public interface SprintRepository extends JpaRepository<Sprint, Integer>{
	//not in DB
	//List<Sprint> findByOwnerId(Integer owner_id);
}
