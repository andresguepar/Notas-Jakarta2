package com.example.notasjakarta.repository.impl;


import com.example.notasjakarta.annotations.MysqlConn;
import com.example.notasjakarta.domain.model.Student;
import com.example.notasjakarta.mapping.dtos.StudentDto;
import com.example.notasjakarta.mapping.mapper.StudentMapper;
import com.example.notasjakarta.repository.Repository;
import com.example.notasjakarta.services.impl.ServiceJdbcException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@ApplicationScoped
public class StudentRepositoryImpl implements Repository<StudentDto> {
    @Inject
    @MysqlConn
    private Connection conn;

    private StudentDto createStudents(ResultSet resultSet) throws
            SQLException {
        Student student = new Student();
        student.setId(resultSet.getLong("id"));
        student.setName(resultSet.getString("name"));
        student.setEmail(resultSet.getString("email"));
        student.setSemester(resultSet.getString("semester"));

        return StudentMapper.mapFrom(student);
    }

    @Override
    public List<StudentDto> list() throws ServiceJdbcException {
        List<StudentDto> studentList = new ArrayList<>();
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from students")) {
            while (resultSet.next()) {
                StudentDto student = createStudents(resultSet);
                studentList.add(student);
            }

        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());

        }
        return studentList;
    }

    @Override
    public StudentDto byId(Long id) {
        StudentDto student = null;
        try (PreparedStatement preparedStatement = conn
                .prepareStatement("SELECT * FROM students WHERE id =?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student = createStudents(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public void add(StudentDto studentDto) {
        String sql;
        Student student = StudentMapper.mapFrom(studentDto);
        if (student.getId() != null && student.getId()>0 ) {
            sql = "UPDATE students SET name=?,email=?,semester=? WHERE id=?";
        }else {
            sql = "INSERT INTO students (name,email,semester) VALUES (?,?,?);";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getSemester());


            if (student.getId() != null && student.getId()>0){
                stmt.setLong(4, student.getId());
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM students WHERE id =?")){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
