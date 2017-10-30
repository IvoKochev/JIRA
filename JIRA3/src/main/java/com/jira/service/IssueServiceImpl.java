package com.jira.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.contract.IIssueService;
import com.jira.exceptions.ResourceNotFoundException;
import com.jira.model.Issue;
import com.jira.repository.IssueRepository;


@Service
public class IssueServiceImpl implements IIssueService {

    @Autowired
    private IssueRepository issueRepository;
    
    @Override
    public void saveIssue(Issue issue) {
        if(issue != null) {
            issueRepository.save(issue);
        }
    }

    @Override
    public void updateIssue(Issue issue) throws ResourceNotFoundException {
        //to be done
    }
    @Override
    public Issue getIssue(int id) {
        return issueRepository.findById(id);
    }

	
}
