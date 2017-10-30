package com.jira.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.contract.ISprintService;
import com.jira.exceptions.ResourceNotFoundException;
import com.jira.exceptions.SprintException;
import com.jira.model.Sprint;
import com.jira.repository.SprintRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



@Service("sprintService")
public class SprintServiceImpl implements ISprintService {
    @Autowired
    private SprintRepository sprintRepository;

    @Override
    public Sprint findSprintById(int id) {
        return sprintRepository.findById(id);
    }

    @Override
    public void removeSprintById(int id) throws ResourceNotFoundException {
        if(sprintRepository.findById(id) == null) {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = null;
            Sprint sprint = null;
            session = sessionFactory.getCurrentSession();
            sprint = (Sprint)session.load(Sprint.class,id);
            session.delete(sprint);
            session.flush() ;
        } else {
            throw new ResourceNotFoundException("Sprint not found");
        }
    }

   
    @Override
    public void saveSprint(Sprint sprint) throws SprintException{
       this.sprintRepository.save(sprint);
    }

	

}
