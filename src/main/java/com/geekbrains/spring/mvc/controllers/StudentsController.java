package com.geekbrains.spring.mvc.controllers;

import com.geekbrains.spring.mvc.model.Student;
import com.geekbrains.spring.mvc.services.student.IStudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// root: http://localhost:8189/app/students

@Controller
@RequestMapping("/students")
public class StudentsController {
    private final IStudentsService studentsService;

    @Autowired
    public StudentsController(@Qualifier(value = "studentsServiceImpl") IStudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @GetMapping
    public String showAllStudents(Model model) {
        List<Student> students = studentsService.findAll();
        model.addAttribute("students", students);
        return "all_students";
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "add_student_form";
    }

    // public String saveNewStudent(@RequestParam Long id, @RequestParam String name, @RequestParam int score) {
    @PostMapping("/add")
    public String saveNewStudent(@ModelAttribute Student newStudent) {
        studentsService.saveOrUpdateStudent(newStudent);
        return "redirect:/students/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentsService.findById(id));
        return "edit_student_form";
    }

    @PostMapping("/edit")
    public String modifyStudent(@ModelAttribute Student modifiedStudent) {
        studentsService.saveOrUpdateStudent(modifiedStudent);
        return "redirect:/students/";
    }

    @GetMapping("/json/{id}")
    @ResponseBody
    public Student showJsonStundent(@PathVariable Long id) {
        return studentsService.findById(id);
    }
}
