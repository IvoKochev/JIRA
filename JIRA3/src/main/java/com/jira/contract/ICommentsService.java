package com.jira.contract;

import com.jira.model.Comments;


public interface ICommentsService {
    
    void saveComment(Comments comment);
    
    Comments findComment(int id);
    
    void deleteComment(Comments comment);
    
}
