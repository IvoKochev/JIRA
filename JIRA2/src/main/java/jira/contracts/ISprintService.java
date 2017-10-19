/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jira.contracts;

import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import jira.exceptions.ResourceNotFoundException;
import jira.models.Sprint;

/**
 *
 * @author ivo
 */
public interface ISprintService {
    
    List<Sprint> getSprintList(HttpServletRequest request) throws ResourceNotFoundException;
    boolean checkSprintDate(String sprintName, LocalDateTime endDate, HttpServletRequest request) throws ResourceNotFoundException;
    
    
    
}
