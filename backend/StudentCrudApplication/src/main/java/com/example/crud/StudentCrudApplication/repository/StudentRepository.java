package com.example.crud.StudentCrudApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.StudentCrudApplication.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
