package com.jira.contract;


import com.jira.exceptions.ResourceNotFoundException;
import com.jira.exceptions.SprintException;
import com.jira.model.Sprint;

public interface ISprintService {
    Sprint findSprintById(int id);
    void removeSprintById(int id) throws ResourceNotFoundException;
    void saveSprint(Sprint sprint) throws SprintException;
    
}
