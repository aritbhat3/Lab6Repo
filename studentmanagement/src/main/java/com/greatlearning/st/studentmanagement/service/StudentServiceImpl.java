package com.greatlearning.st.studentmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.st.studentmanagement.entity.Student;
import com.greatlearning.st.studentmanagement.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

	
	@Autowired
	StudentRepository studentrepository;
	
	@Override
	public List<Student> getAllStudent() {
		return studentrepository.findAll();
	}

	@Override
	public void save(Student student) {
		studentrepository.save(student);
		
	}

	@Override
	public void deleteById(int id) {
		Student student = getStudentById(id);
		studentrepository.delete(student);
		
	}

	@Override
	public List<Student> search(String firstname, String lastname, String course) {
		studentrepository.findByFnameContainsAndLnameContainsAndCourseContainsAllIgnoreCase(firstname, lastname, course);
		return null;
	}

	@Override
	public Student getStudentById(int id) {
		Optional<Student> optStud = studentrepository.findById(id);
		if (optStud.isPresent()) {
			return optStud.get();
		}
		return null;
	}

}
