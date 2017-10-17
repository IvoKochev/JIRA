package jira.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import jira.contracts.IIssueService;
import jira.db.HibernateUtils;
import jira.exceptions.IssueException;
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

	@Override
	public Issue updateIssue(Issue issue) throws IssueException {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Issue issue2 = (Issue) session.get(Issue.class, issue.getId());
		if (issue2 == null) {
			throw new IssueException("Issue Not Found");
		}
		issue2.setPriority(issue.getPriority());
		issue2.setStatus(issue.getStatus());
		issue2.setSummary(issue.getSummary());
		issue2.setType(issue.getType());
		session.update(issue2);
		transaction.commit();
		session.close();
		return issue2;
	}
}
