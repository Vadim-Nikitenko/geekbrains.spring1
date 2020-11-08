package com.geekbrains.spring.mvc.repositories.student;

import com.geekbrains.spring.mvc.model.Student;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class StudentsRepository implements IStudentsDataProvider {
    private List<Student> students;
    private Long maxId;

    @PostConstruct
    private void init() {
        this.students = new ArrayList<>();
        this.students.add(new Student(1L, "Bob", 70));
        this.students.add(new Student(2L, "John", 85));
        this.maxId = 2L;
    }

    @Override
    public List<Student> findAll() {
        return Collections.unmodifiableList(students);
    }

    @Override
    public Student saveOrUpdateStudent(Student student) {
        if (student.getId() == null) {
            maxId++;
            student.setId(maxId);
            students.add(student);
            return student;
        } else {
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getId().equals(student.getId())) {
                    students.set(i, student);
                    return student;
                }
            }
        }
        throw new RuntimeException("What???");
    }

    @Override
    public Student findById(Long id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        throw new RuntimeException("Student not found");
    }
}
