package com.jira.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jira.contract.IAttachmentService;
import com.jira.model.Attachment;
import com.jira.repository.AttachmentRepository;

@Service("attachmentService")
public class AttachmentServiceImpl implements IAttachmentService {

	@Autowired
	private AttachmentRepository attachmentRepository;

	@Override
	public void uploadAttachment(Attachment attachment) {
		attachmentRepository.save(attachment);
	}

	@Override
	public void removeAttachment(Attachment attachment) {

	}

	@Override
	public List<Attachment> findAttachments(int uploaderId) {
		return null;
	}

	@Override
	public void deleteAttachment(Attachment attachment) {
		File file = new File(attachment.getLocation());
		file.delete();
	}

	@Override
	public int findMaxId() {
		return attachmentRepository.findMaxId();
	}

	@Override
	public ResponseEntity<byte[]> getFile(int id) {
		Attachment attachmet = this.attachmentRepository.findOne(id);
		File file = new File(attachmet.getLocation());
		HttpHeaders headers = new HttpHeaders();
		headers.add("filename", attachmet.getName());
		byte[] bytes = null;
		try {
			bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().headers(headers).body(bytes);
	}

}
