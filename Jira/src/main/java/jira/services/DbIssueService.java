package jira.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import jira.contracts.IIssueService;
import jira.db.HibernateUtils;
import jira.exceptions.ResourceNotFoundException;
import jira.models.Issue;
import jira.models.UserHasIssue;

@Service
public class DbIssueService implements IIssueService {

	@Override
	// unfinished
	public Issue createIssue(Issue issue, HttpServletRequest request) {
            try (Session session = HibernateUtils.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.save(issue);
                transaction.commit();
                Transaction transaction2 = session.beginTransaction();
                UserHasIssue userHasIssue = new UserHasIssue();
                userHasIssue.setIssue_id(issue.getId());
                userHasIssue.setUsers_id((int) request.getSession().getAttribute("user_id"));
                session.save(userHasIssue);
                transaction2.commit();
            }
		return issue;
	}

	@Override
	public Issue updateIssue(Issue issue) throws ResourceNotFoundException {
            Issue issue2;
            try (Session session = HibernateUtils.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                issue2 = (Issue) session.get(Issue.class, issue.getId());
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
            }
		return issue2;
	}

	@Override
	// unfinished
	public List<Issue> issueList(HttpServletRequest request) throws ResourceNotFoundException {
            @SuppressWarnings("unchecked")
                    List<Issue> issues;
            try (Session session = HibernateUtils.getSessionFactory().openSession()) {
                Criteria criteria = session.createCriteria(Issue.class);
                // criteria.add(Restrictions.eq("project_id",
                // DbProjectService.current_project_id));
                issues = criteria.list();
                if (issues == null) {
                    throw new ResourceNotFoundException("Issues' not found");
                }
            }
		return issues;
	}

	@Override
	// unfinished
	public Issue getIssue(int id, HttpServletRequest request) {
            Issue issue;
            try (Session session = HibernateUtils.getSessionFactory().openSession()) {
                Criteria criteria = session.createCriteria(Issue.class);
                criteria.add(Restrictions.eq("id", id));
                issue = (Issue) criteria.uniqueResult();
                if (issue == null) {
                    throw new ResourceNotFoundException("Issue not found");
                }
            }
		return issue;
	}
}
