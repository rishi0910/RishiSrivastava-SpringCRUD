package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // 1. READ ALL
    @GetMapping
    public List<Student> getAll() {
        return service.getAllStudents();
    }

    // 2. CREATE (This is the one you were missing!)
    @PostMapping
    public String add(@RequestBody Student student) {
        service.addStudent(student);
        return "Student added successfully!";
    }

    // 3. READ ONE
    @GetMapping("/{id}")
    public Student getById(@PathVariable Integer id) {
        return service.getStudentById(id);
    }

    // 4. UPDATE
    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, @RequestBody Student student) {
        service.updateStudent(student, id);
        return "Student updated successfully!";
    }

    // 5. DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        service.deleteStudent(id);
        return "Student deleted successfully!";
    }
}