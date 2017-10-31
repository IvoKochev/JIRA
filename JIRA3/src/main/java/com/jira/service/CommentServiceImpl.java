/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jira.service;

import com.jira.contract.ICommentsService;
import com.jira.model.Comments;
import com.jira.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ivo
 */
@Service
public class CommentServiceImpl implements ICommentsService{
    @Autowired
    private CommentRepository commentRepository;
    
    @Override
    public void saveComment(Comments comment) {
        if(comment != null) {
            commentRepository.save(comment);
        }
    }

    @Override
    public Comments findComment(int id) {
        return commentRepository.findById(id);
    }
    
    @Override
    public void deleteComment(Comments comment) {
       
    }
    
}
