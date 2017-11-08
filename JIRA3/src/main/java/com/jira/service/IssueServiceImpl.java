package com.jira.service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.contract.IIssueService;
import com.jira.model.Issue;
import com.jira.repository.IssueRepository;

@Service
public class IssueServiceImpl implements IIssueService {

	@Autowired
	private IssueRepository issueRepository;

	@Override
	public void saveIssue(Issue issue) {
		if (issue != null) {
			switch (issue.getType()) {
			case "Epic":
				issue.setImgUrl("/images/epic.png");
				break;
			case "Story":
				issue.setImgUrl("/images/story.png");
				break;
			case "Task":
				issue.setImgUrl("/images/task.png");
				break;

			default:
				issue.setImgUrl("/images/bug.png");
				break;
			}
			issueRepository.save(issue);
		}
	}

	@Override
	public Issue getIssue(int id) {
		return issueRepository.findById(id);
	}
        
        @Override
        public boolean isValidEmailAddress(String email) {
            boolean result = true;
               try {
                    InternetAddress emailAddr = new InternetAddress(email);
                    emailAddr.validate();
                 } catch (AddressException ex) {
                    result = false;
                 }
                 return result;
}

    @Override
    public void deleteIssue(int id) {
        issueRepository.deleteIssue(id);
    }
}
