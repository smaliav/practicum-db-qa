package ru.practicum.smaliav.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.practicum.smaliav.model.Student;
import ru.practicum.smaliav.model.StudentWithGroupName;

@RequiredArgsConstructor
@Repository
public class StudentDaoImpl implements StudentDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Student getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM student WHERE id = ?", getStudentMapper(), id);
    }

    @Override
    public StudentWithGroupName getByIdWithGroupName(int id) {
        return jdbcTemplate.queryForObject(
            "SELECT s.id   AS id,\n" +
                "       s.name AS name,\n" +
                "       g.name AS group_name\n" +
                "FROM student AS s\n" +
                "JOIN study_group AS g ON s.group_id = g.id\n" +
                "WHERE s.id = ?",
            getStudentWithGroupNameMapper(),
            id
        );
    }

    @Override
    public Student save(Student student) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("student")
            .usingGeneratedKeyColumns("id");
        int id = insert.executeAndReturnKey(studentToMap(student)).intValue();
        return student.setId(id);
    }

    @Override
    public void save(List<Student> newStudents) {
        jdbcTemplate.batchUpdate("INSERT INTO student (name, group_id) VALUES (?, ?);",
            new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    Student student = newStudents.get(0);
                    ps.setString(1, student.getName());
                    ps.setInt(2, student.getGroupId());
                }

                @Override
                public int getBatchSize() {
                    return newStudents.size();
                }
            }
        );
    }

    private static RowMapper<Student> getStudentMapper() {
        return (rs, rowNum) -> new Student(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("group_id")
        );
    }

    private static RowMapper<StudentWithGroupName> getStudentWithGroupNameMapper() {
        return (rs, rowNum) -> new StudentWithGroupName(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("group_name")
        );
    }

    private static Map<String, Object> studentToMap(Student student) {
        return Map.of(
            "name", student.getName(),
            "group_id", student.getGroupId()
        );
    }
}
