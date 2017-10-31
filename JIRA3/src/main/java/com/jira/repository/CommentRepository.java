/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jira.repository;

import com.jira.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ivo
 */
@Repository("commentRepository")
public interface CommentRepository extends JpaRepository<Comments, Long>{
    public Comments findById(Integer id);
}
