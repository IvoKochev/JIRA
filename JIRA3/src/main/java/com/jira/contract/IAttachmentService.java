package com.jira.contract;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jira.model.Attachment;

public interface IAttachmentService {
	public void uploadAttachment(Attachment attachment);

	public void removeAttachment(Attachment attachment);

	List<Attachment> findAttachments(int uploaderId);

	public void deleteAttachment(Attachment attachment);

	public int findMaxId();
	
	public  ResponseEntity<byte[]>  getFile(int id);

}
