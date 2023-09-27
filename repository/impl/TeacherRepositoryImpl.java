package com.example.notasjakarta.repository.impl;


import com.example.notasjakarta.domain.model.Teacher;
import com.example.notasjakarta.mapping.dtos.TeacherDto;
import com.example.notasjakarta.mapping.mapper.TeacherMapper;
import com.example.notasjakarta.repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepositoryImpl implements Repository<TeacherDto> {
    private Connection conn;

    public TeacherRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    private TeacherDto createTeacher(ResultSet resultSet) throws
            SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getLong("id_teacher"));
        teacher.setName(resultSet.getString("name"));
        teacher.setEmail(resultSet.getString("email"));
        return TeacherMapper.mapFrom(teacher);
    }

    @Override
    public List<TeacherDto> list() {
        List<TeacherDto> teacherList = new ArrayList<>();
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from teachers")) {
            while (resultSet.next()) {
                TeacherDto teacher = createTeacher(resultSet);
                teacherList.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return teacherList;
    }

    @Override
    public TeacherDto byId(Long id) {
        TeacherDto teacher = null;
        try (PreparedStatement preparedStatement = conn
                .prepareStatement("SELECT * FROM teachers WHERE id_teacher =?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                teacher = createTeacher(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }
    @Override
    public void add(TeacherDto teacherDto) {
        String sql;
        Teacher teacher = TeacherMapper.mapFrom(teacherDto);
        if (teacher.getId() != null && teacher.getId()>0 ) {
            sql = "UPDATE teachers SET name=?,email=? WHERE id=?";
        }else {
            sql = "INSERT INTO teachers(name,email) VALUES(?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, teacher.getName());
            stmt.setString(2, teacher.getEmail());

           if (teacher.getId() != null && teacher.getId()>0){
               stmt.setLong(3, teacher.getId());
            }

           stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM teachers WHERE id =?")){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
