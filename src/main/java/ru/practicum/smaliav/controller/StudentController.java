package ru.practicum.smaliav.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.smaliav.model.Student;
import ru.practicum.smaliav.model.StudentWithGroupName;
import ru.practicum.smaliav.service.StudentService;

@RequiredArgsConstructor
@RequestMapping("/student")
@RestController
public class StudentController {
    private final StudentService service;

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = service.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/{id}/verbose")
    public ResponseEntity<StudentWithGroupName> getStudentWithGroupNameById(@PathVariable int id) {
        StudentWithGroupName student = service.getStudentWithGroupNameById(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student newStudent) {
        Student savedStudent = service.saveStudent(newStudent);
        return ResponseEntity.ok(savedStudent);
    }

    @PostMapping("/multiple")
    public void saveStudents(@RequestBody List<Student> newStudents) {
        service.saveStudents(newStudents);
    }
}
