package ru.practicum.smaliav.dao;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.practicum.smaliav.model.Student;
import ru.practicum.smaliav.model.StudentWithGroupName;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@JdbcTest
public class StudentDaoImplTest {
    private final JdbcTemplate jdbcTemplate;

    private StudentDaoImpl dao;

    @BeforeEach
    public void setUp() {
        dao = new StudentDaoImpl(jdbcTemplate);
    }

    @Test
    public void testGetStudentById() {
        // Подготовка
        Student student = new Student(1, "Name", 1);
        dao.save(student);

        // Действие
        Student res = dao.getById(1);

        // Проверка
        assertThat(res)
            .usingRecursiveComparison()
            .isEqualTo(student);
    }

    @Test
    public void testGetStudentWithGroupNameById() {
        // Подготовка
        Student student = new Student(1, "Name", 1);
        dao.save(student);

        // Действие
        StudentWithGroupName res = dao.getByIdWithGroupName(1);

        // Проверка
        assertThat(res)
            .usingRecursiveComparison()
            .isEqualTo(new StudentWithGroupName(1, "Name", "UNI-01"));
    }
}
