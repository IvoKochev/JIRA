package jira.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import jira.contracts.IIssueService;
import jira.db.HibernateUtils;
import jira.exceptions.ResourceNotFoundException;
import jira.models.Issue;

@Service
public class DbIssueService implements IIssueService {

	@Override
	public Issue createIssue(Issue issue, HttpServletRequest request) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		issue.setSprint_id((Integer) request.getSession().getAttribute("user_id"));
		session.save(issue);
		transaction.commit();
		session.close();
		return issue;
	}

	@Override
	public Issue updateIssue(Issue issue) throws ResourceNotFoundException {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Issue issue2 = (Issue) session.get(Issue.class, issue.getId());
		if (issue2 == null) {
			session.close();
			throw new ResourceNotFoundException("Issue Not Found");
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

	@Override
	// unfinished
	public List<Issue> issueList(HttpServletRequest request) throws ResourceNotFoundException {
		return null;
	}
}
