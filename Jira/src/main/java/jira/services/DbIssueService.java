package jira.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import jira.contracts.IIssueService;
import jira.db.HibernateUtils;
import jira.models.Issue;

@Service
public class DbIssueService implements IIssueService {

	@Override
	public Issue createIssue(Issue issue) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
	
		session.save(issue);
		transaction.commit();
		session.close();
		return issue;
	}

}
