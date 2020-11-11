package com.geekbrains.spring.mvc.repositories.student;

import com.geekbrains.spring.mvc.model.Student;

import java.util.List;

public interface IStudentsDataProvider {
    List<Student> findAll();
    Student saveOrUpdateStudent(Student student);
    Student findById(Long id);
}
