package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public void addStudent(Student s) {
        repo.save(s);
    }

    public Student getStudentById(Integer id) {
        return repo.findById(id);
    }

    public void updateStudent(Student s, Integer id) {
        repo.update(s, id);
    }

    public void deleteStudent(Integer id) {
        repo.delete(id);
    }
}