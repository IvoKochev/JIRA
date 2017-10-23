package com.jira.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jira.model.Attachment;

@Repository("attachmentRepository")
public interface AttachmentRepository extends JpaRepository<Attachment, Long>{
	List<Attachment> findAttachmentByUploaderId(int uploaderId);
}
