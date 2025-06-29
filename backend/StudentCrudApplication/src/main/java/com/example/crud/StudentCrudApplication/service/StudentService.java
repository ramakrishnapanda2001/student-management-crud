package com.example.crud.StudentCrudApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.crud.StudentCrudApplication.model.Student;
import com.example.crud.StudentCrudApplication.repository.StudentRepository;

@Service
public class StudentService {
	private final StudentRepository studentRepo;
	public StudentService(StudentRepository studentRepo) {
		this.studentRepo=studentRepo;
	}
	public List<Student> getAllStudent(){
		return studentRepo.findAll();
	}
	public Student getStudentById(Long id) {
        Optional<Student> studentOptional = studentRepo.findById(id);
        return studentOptional.orElse(null);
    }
	public Student saveStudent(Student student) {
		return studentRepo.save(student);
	}
	
	public void deleteStudent(Long id) {
        studentRepo.deleteById(id);
    }
}
