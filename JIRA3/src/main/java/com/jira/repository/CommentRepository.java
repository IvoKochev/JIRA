/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jira.repository;

import com.jira.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ivo
 */
@Repository("commentRepository")
@Transactional
public interface CommentRepository extends JpaRepository<Comments, Long>{
    public Comments findById(Integer id);
    
    @Modifying
    @Query(value = "DELETE FROM comments WHERE id =:id", nativeQuery = true)
    void deleteComment(@Param("id") int id);
}
