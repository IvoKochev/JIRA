package com.jira.contract;

import javax.servlet.http.HttpServletRequest;

import com.jira.model.Attachment;

public interface IAttachmentService {
	public void uploadAttachment(HttpServletRequest request, Attachment attachment);
	public void removeAttachment(Attachment attachment);
	public void updateAttachment(Attachment attachment);

}
