package ru.practicum.smaliav.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.smaliav.dao.StudentDao;
import ru.practicum.smaliav.model.Student;
import ru.practicum.smaliav.model.StudentWithGroupName;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentDao dao;

    @Override
    public Student getStudentById(int id) {
        return dao.getById(id);
    }

    @Override
    public StudentWithGroupName getStudentWithGroupNameById(int id) {
        return dao.getByIdWithGroupName(id);
    }

    @Override
    public Student saveStudent(Student student) {
        return dao.save(student);
    }

    @Override
    public void saveStudents(List<Student> newStudents) {
        dao.save(newStudents);
    }
}
