/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jira.services;

import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import jira.contracts.ISprintService;
import jira.db.HibernateUtils;
import jira.exceptions.ResourceNotFoundException;
import jira.models.Project;
import jira.models.Sprint;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

/**
 *
 * @author ivo
 */
@Service
public class DBSprintService implements ISprintService{

    @Override
    public List<Sprint> getSprintList(HttpServletRequest request) throws ResourceNotFoundException {
        List<Sprint> sprints = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
                Criteria criteria = session.createCriteria(Sprint.class);
                criteria.add(Restrictions.eq("id", request.getSession().getAttribute("id")));
                //sprints = castList(Project.class, criteria.list());
                if (sprints == null || sprints.isEmpty()) {
                    throw new ResourceNotFoundException("There are no sprints");
                }
            }
        return sprints;
    }

    @Override
    public boolean checkSprintDate(String sprintName, LocalDateTime endDate, HttpServletRequest request) throws ResourceNotFoundException {
        return true;
    }
    
}
