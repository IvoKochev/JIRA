package com.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jira.model.Attachment;

@Repository("attachmentRepository")
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
	Attachment findById(Integer id);

	@Query(value = "SELECT MAX(id) FROM attachments", nativeQuery = true)
	int findMaxId();
}
