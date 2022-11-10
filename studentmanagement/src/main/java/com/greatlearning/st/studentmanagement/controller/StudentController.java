package com.greatlearning.st.studentmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greatlearning.st.studentmanagement.entity.Student;
import com.greatlearning.st.studentmanagement.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studentservice;

	@RequestMapping("/list")
	public String getAllStudent(Model model) {
		List<Student> students = studentservice.getAllStudent();
		model.addAttribute("Student", students);
		return "list-Students";
	}

	@RequestMapping("/showFormForAdd")
	public String FormForAdd(Model model) {
		Student student = new Student();
		model.addAttribute("Student", student);
		return "Student-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String FormForUpdate(Model model, @RequestParam("studentId") int studentId) {
		Student student = studentservice.getStudentById(studentId);
		model.addAttribute("Student", student);
		return "Student-form";
	}

	@RequestMapping("/delete")
	public String deleteStudent(@RequestParam("studentId") int studentId) {
		studentservice.deleteById(studentId);
		return "redirect:/student/list";
	}

	@RequestMapping("/search")
	public String searchStudent(@RequestParam("fname") String fname, @RequestParam("lname") String lname,
			@RequestParam("course") String course, Model model) {
		if ((fname == null && lname == null && course == null)
				|| (fname.trim().equals("") && lname.trim().equals("") && course.trim().equals(""))) {
			return "redirect:/student/list";
		}
		List<Student> students = studentservice.search(fname, lname, course);
		model.addAttribute("Student", students);
		return "list-Students";
	}

	@PostMapping("/save")
	public String saveStudent(@RequestParam("fname") String fname, @RequestParam("lname") String lname,
			@RequestParam("course") String course, @RequestParam("country") String country, @RequestParam("id") int id, Model model) {
		Student student = new Student();
		System.out.print(id);
		if (id!=0) {
			studentservice.getStudentById(id);
		}
		else {
			student = new Student();
		}
		student.setFname(fname);
		student.setCountry(country);
		student.setCourse(course);
		student.setLname(lname);
		studentservice.save(student);
		return "redirect:/student/list";
	}
}
