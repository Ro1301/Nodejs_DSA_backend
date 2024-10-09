package com.sjprogramming.restapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sjprogramming.restapi.entity.Student;
import com.sjprogramming.restapi.repository.StudentRepository;

import jakarta.annotation.Resource;

@RestController
public class StudentControler {
	
	@Resource
	StudentRepository repo;

	@GetMapping("/students")
	public List<Student> getAllStudents(){
		 List<Student> students = repo.findAll();
		  return students;
	}
	
	//localhost:8080/students/1
	@GetMapping("/students/{id}")
	public Student getStudent(@PathVariable long id) {
		Student student = repo.findById(id).get();
		
		return student;
		
	}
	
	@PostMapping("/student/add")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		Student newStudent = repo.save(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
		
	}
	
	@PutMapping("/student/update/{id}")
	public Student updateStudents(@PathVariable long id) {
	   Student student = repo.findById(id).get();
	   student.setName("Faci");
	   student.setPercentage(92);
	   repo.save(student);
	   return student;
		
	}
	@DeleteMapping("/student/delete/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removeStudent(@PathVariable long id) {
		Student student = repo.findById(id).get();
		repo.delete(student);
	}

}