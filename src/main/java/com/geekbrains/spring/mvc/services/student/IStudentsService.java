package com.geekbrains.spring.mvc.services.student;

import com.geekbrains.spring.mvc.model.Student;

import java.util.List;

public interface IStudentsService {
    List<Student> findAll();
    Student saveOrUpdateStudent(Student student);
    Student findById(Long id);
}
