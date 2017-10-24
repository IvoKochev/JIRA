//package com.jira.service;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.hibernate.Criteria;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.stereotype.Service;
//
//import com.jira.cotract.IIssueService;
//import com.jira.db.HibernateUtils;
//import com.jira.exceptions.ResourceNotFoundException;
//import com.jira.model.Issue;
//
//
//@Service
//public class DbIssueService implements IIssueService {
//
//	@Override
//	// unfinished
//	public Issue createIssue(Issue issue, HttpServletRequest request) {
//            try (Session session = HibernateUtils.getSessionFactory().openSession()) {
//                Transaction transaction = session.beginTransaction();
//                session.save(issue);
//                transaction.commit();
//                Transaction transaction2 = session.beginTransaction();
//               // UserHasIssue userHasIssue = new UserHasIssue();
////                userHasIssue.setIssue_id(issue.getId());
////                userHasIssue.setUsers_id((int) request.getSession().getAttribute("user_id"));
////                session.save(userHasIssue);
//                transaction2.commit();
//            }
//		return issue;
//	}
//
//	@Override
//	public Issue updateIssue(Issue issue) throws ResourceNotFoundException {
//            Issue issue2;
//            try (Session session = HibernateUtils.getSessionFactory().openSession()) {
//                Transaction transaction = session.beginTransaction();
//                issue2 = session.get(Issue.class, issue.getId());
//                if (issue2 == null) {
//                    session.close();
//                    throw new ResourceNotFoundException("Issue Not Found");
//                }
//                issue2.setPriority(issue.getPriority());
//                issue2.setStatus(issue.getStatus());
//                issue2.setSummary(issue.getSummary());
//                issue2.setType(issue.getType());
//                session.update(issue2);
//                transaction.commit();
//            }
//		return issue2;
//	}
//
//	@Override
//	// unfinished
//	public List<Issue> issueList(HttpServletRequest request) throws ResourceNotFoundException {
//          //  List<Issue> issues;
////            try (Session session = HibernateUtils.getSessionFactory().openSession()) {
////                Criteria criteria = session.createCriteria(Issue.class);
////                // criteria.add(Restrictions.eq("project_id",
////                // DbProjectService.current_project_id));
////                issues = criteria.list();
////                if (issues == null) {
////                    throw new ResourceNotFoundException("Issues' not found");
////                }
////            }
//		return null;
//	}
//
//	@Override
//	// unfinished
//	public Issue getIssue(int id, HttpServletRequest request) {
//            Issue issue;
//            try (Session session = HibernateUtils.getSessionFactory().openSession()) {
//                Criteria criteria = session.createCriteria(Issue.class);
//                criteria.add(Restrictions.eq("id", id));
//                issue = (Issue) criteria.uniqueResult();
//                if (issue == null) {
//                    throw new ResourceNotFoundException("Issue not found");
//                }
//            }
//		return issue;
//	}
//}
