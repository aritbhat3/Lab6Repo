package com.greatlearning.st.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.greatlearning.st.studentmanagement.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{
	
	@Query("Select u from users u where username = ?1")
	public Users getUserByUserName(String username);
}
