package com.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jira.model.User;

@Repository("userRepository")
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);

	User findByid(int user_id);
	@Modifying
	@Query(value="UPDATE users u SET u.rating=:rating,votecounter=:votes WHERE u.id=:id",nativeQuery = true)
	void updateUserRating(@Param("id") Integer id, @Param("rating") double rating, @Param("votes") Integer votes);
}
