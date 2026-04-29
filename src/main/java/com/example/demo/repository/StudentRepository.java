package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class StudentRepository {
    private final JdbcTemplate jdbc;

    public StudentRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // This tells Java how to turn a Database Row into a Student Object
    private final RowMapper<Student> mapper = (rs, rowNum) -> new Student(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("course")
    );

    public List<Student> findAll() {
        return jdbc.query("SELECT * FROM student", mapper);
    }

    // FIXES: Cannot resolve method 'findById'
    public Student findById(Integer id) {
        return jdbc.queryForObject("SELECT * FROM student WHERE id = ?", mapper, id);
    }

    public int save(Student s) {
        return jdbc.update("INSERT INTO student (name, email, course) VALUES (?, ?, ?)",
                s.getName(), s.getEmail(), s.getCourse());
    }

    // FIXES: Cannot resolve method 'update'
    public int update(Student s, Integer id) {
        return jdbc.update("UPDATE student SET name=?, email=?, course=? WHERE id=?",
                s.getName(), s.getEmail(), s.getCourse(), id);
    }

    // FIXES: Cannot resolve method 'delete'
    public int delete(Integer id) {
        return jdbc.update("DELETE FROM student WHERE id = ?", id);
    }
}