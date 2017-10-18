package jira.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import jira.contracts.IProjectService;
import jira.db.HibernateUtils;
import jira.exceptions.ResourceNotFoundException;
import jira.models.Project;

@Service
public class DbProjectService implements IProjectService {

	@Override
	public List<Project> getProjectList(HttpServletRequest request) throws ResourceNotFoundException {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Project.class);
		criteria.add(Restrictions.eq("user_id", request.getSession().getAttribute("user_id")));
		List<Project> projects = castList(Project.class, criteria.list());
		if (projects == null || projects.isEmpty()) {
			session.close();
			throw new ResourceNotFoundException("There are no open projects");
		}
		session.close();
		return projects;
	}

	private static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
		List<T> r = new ArrayList<T>(c.size());
		for (Object o : c)
			r.add(clazz.cast(o));
		return r;
	}

}
