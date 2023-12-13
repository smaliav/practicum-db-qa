package ru.practicum.smaliav.service;

import java.util.List;

import ru.practicum.smaliav.model.Student;
import ru.practicum.smaliav.model.StudentWithGroupName;

public interface StudentService {
    Student getStudentById(int id);
    StudentWithGroupName getStudentWithGroupNameById(int id);
    Student saveStudent(Student student);
    void saveStudents(List<Student> newStudents);
}
