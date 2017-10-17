package jira.services;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
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
			session.close();
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

	@Override
	public List<Issue> issueList() throws IssueException {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Issue.class);
		criteria.add(Restrictions.eq("project_id", DbProjectService.current_project_id));
		@SuppressWarnings("unchecked")
		List<Issue> issues = criteria.list();
		if (issues == null) {
			session.close();
			throw new IssueException("'Issues' not found");
		}
		session.close();
		return issues;
	}
}
