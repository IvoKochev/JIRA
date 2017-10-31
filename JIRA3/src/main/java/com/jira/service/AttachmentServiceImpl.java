package com.jira.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.contract.IAttachmentService;
import com.jira.model.Attachment;
import com.jira.repository.AttachmentRepository;
@Service("attachmentService")
public class AttachmentServiceImpl implements IAttachmentService{
	
	@Autowired
	private AttachmentRepository attachmentRepository;

	@Override
	public void uploadAttachment(HttpServletRequest request, Attachment attachment) {
		int uploader_id = (int) request.getSession().getAttribute("user_id");
	}

	@Override
	public void removeAttachment(Attachment attachment) {
		
		
	}

	@Override
	public void updateAttachment(Attachment attachment) {
		
		
	}


}
