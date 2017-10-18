package jira.contracts;

import org.springframework.data.jpa.repository.JpaRepository;

import jira.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}