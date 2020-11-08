package com.geekbrains.spring.mvc.services.student;

import com.geekbrains.spring.mvc.model.Student;
import com.geekbrains.spring.mvc.repositories.product.IProductDataProvider;
import com.geekbrains.spring.mvc.repositories.student.IStudentsDataProvider;
import com.geekbrains.spring.mvc.repositories.student.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsServiceImpl implements IStudentsService {
    private IStudentsDataProvider studentsRepository;

    @Autowired
    public void setProductsRepository(@Qualifier(value = "studentsRepository") IStudentsDataProvider studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentsRepository.findAll();
    }

    @Override
    public Student saveOrUpdateStudent(Student student) {
        return studentsRepository.saveOrUpdateStudent(student);
    }

    @Override
    public Student findById(Long id) {
        return studentsRepository.findById(id);
    }
}
