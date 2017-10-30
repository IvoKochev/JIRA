package com.jira.service;

import com.jira.contract.IIssueService;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;


import com.jira.exceptions.ResourceNotFoundException;
import com.jira.model.Issue;
import com.jira.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;


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
