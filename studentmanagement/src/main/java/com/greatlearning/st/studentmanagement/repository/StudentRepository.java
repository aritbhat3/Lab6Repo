package com.greatlearning.st.studentmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.st.studentmanagement.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	public List<Student> findByFnameContainsAndLnameContainsAndCourseContainsAllIgnoreCase(String fname,String lname, String course);
}
 