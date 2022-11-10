package com.greatlearning.st.studentmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
@ComponentScan
public class StudentmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentmanagementApplication.class, args);
	}
}