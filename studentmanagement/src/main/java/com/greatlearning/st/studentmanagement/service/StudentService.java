package com.greatlearning.st.studentmanagement.service;

import java.util.List;

import com.greatlearning.st.studentmanagement.entity.Student;

public interface StudentService {

	public List<Student> getAllStudent();
	public void save (Student student);
	public void deleteById(int id);
	public List<Student> search(String firstname, String lastname, String course);
	public Student getStudentById(int id);
}
