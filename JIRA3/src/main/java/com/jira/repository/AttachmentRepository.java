package com.jira.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jira.model.Attachment;

<<<<<<< HEAD
//@Repository("attachmentRepository")
//public interface AttachmentRepository extends JpaRepository<Attachment, Long>{
//	List<Attachment> findAttachmentByUploaderId(int uploaderId);
//}
=======
@Repository("attachmentRepository")
public interface AttachmentRepository extends JpaRepository<Attachment, Long>{
	//not in DB
	//List<Attachment> findByUploaderid(int owner_id);
}
>>>>>>> 0be362e244bea1b352580cabe16005c8925ea6b9
