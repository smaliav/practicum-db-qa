package ru.practicum.smaliav.dao;

import java.util.List;

import ru.practicum.smaliav.model.Student;
import ru.practicum.smaliav.model.StudentWithGroupName;

public interface StudentDao {
    Student getById(int id);
    StudentWithGroupName getByIdWithGroupName(int id);
    Student save(Student student);
    void save(List<Student> newStudents);
}
