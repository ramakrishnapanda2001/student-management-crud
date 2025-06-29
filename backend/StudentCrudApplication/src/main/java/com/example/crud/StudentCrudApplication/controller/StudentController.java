package com.example.crud.StudentCrudApplication.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.StudentCrudApplication.model.Student;
import com.example.crud.StudentCrudApplication.service.StudentService;

@RestController
@RequestMapping("/api/students")

public class StudentController {
	private final StudentService studentService;
	public StudentController(StudentService studentService) {
		this.studentService=studentService;
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get_students")
	public List<Student> getStudents() {
		return studentService.getAllStudent();
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/save_student")
	public ResponseEntity<?> createStudent(@RequestBody Student student) {
		studentService.saveStudent(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("Student Created"));
	}
	@CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/update_student/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        // Check if the student exists
        Student existingStudent = studentService.getStudentById(id);
        if (existingStudent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Student Not Found"));
        }
        // Update the student details
        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setPhone(student.getPhone());
        existingStudent.setAddress(student.getAddress());
        existingStudent.setDepartment(student.getDepartment());
        // Save the updated student
        studentService.saveStudent(existingStudent);
        return ResponseEntity.ok(new ResponseMessage("Student Updated"));
	}
	
	 @CrossOrigin(origins = "http://localhost:4200")
	    @DeleteMapping("/delete_student/{id}")
	    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
	        Student existingStudent = studentService.getStudentById(id);
	        if (existingStudent == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Student Not Found"));
	        }
	        studentService.deleteStudent(id);
	        return ResponseEntity.ok(new ResponseMessage("Student Deleted"));
	    }
}
class ResponseMessage {
    private String message;
    public ResponseMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
