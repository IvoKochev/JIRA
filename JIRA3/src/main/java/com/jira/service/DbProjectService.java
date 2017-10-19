package com.jira.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.jira.cotract.IProjectService;
import com.jira.db.HibernateUtils;
import com.jira.exceptions.ResourceNotFoundException;
import com.jira.model.Project;


@Service
public class DbProjectService implements IProjectService {

	@Override
	public List<Project> getProjectList(HttpServletRequest request) throws ResourceNotFoundException {
            List<Project> projects;
            try (Session session = HibernateUtils.getSessionFactory().openSession()) {
                Criteria criteria = session.createCriteria(Project.class);
                criteria.add(Restrictions.eq("user_id", request.getSession().getAttribute("user_id")));
                projects = castList(Project.class, criteria.list());
                if (projects == null || projects.isEmpty()) {
                    throw new ResourceNotFoundException("There are no open projects");
                }
            }
		return projects;

	}

	private static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
		List<T> r = new ArrayList<>(c.size());
                c.forEach((o) -> {
                    r.add(clazz.cast(o));
            });
		return r;

	}
}
