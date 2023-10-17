package com.example.notasjakarta.repository.impl;


import com.example.notasjakarta.annotations.MysqlConn;
import com.example.notasjakarta.domain.model.Subject;
import com.example.notasjakarta.domain.model.Teacher;
import com.example.notasjakarta.mapping.dtos.SubjectDto;
import com.example.notasjakarta.mapping.mapper.SubjectMapper;
import com.example.notasjakarta.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@ApplicationScoped
public class SubjectRepositoryImpl implements Repository<SubjectDto> {
    @Inject
    @MysqlConn
    private Connection conn;

    private SubjectDto createSubjetcs(ResultSet resultSet) throws
            SQLException {
        Subject subject = new Subject();
        subject.setId(resultSet.getLong("id_subject"));
        subject.setName(resultSet.getString("name"));

        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getLong("id_teacher"));
        teacher.setName(resultSet.getString("teacher"));
        teacher.setEmail(resultSet.getString("email"));

        subject.setTeacher(teacher);
        return SubjectMapper.mapFrom(subject);
    }


    @Override
    public List<SubjectDto> list() {
        List<SubjectDto> subjectList = new ArrayList<>();
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT s.*, t.name as teacher, t.email " +
                     "FROM subjects s INNER JOIN teachers t ON (s.id_teacher = t.id_teacher) ;")) {
            while (resultSet.next()) {
                SubjectDto subject = createSubjetcs(resultSet);
                subjectList.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return subjectList;
    }

    @Override
    public SubjectDto byId(Long id) {
        SubjectDto subject = null;
        try (PreparedStatement preparedStatement = conn
                .prepareStatement("SELECT s.*, t.name as teacher, t.email " +
                        "FROM subjects s INNER JOIN teachers t ON (s.id_teacher = t.id_teacher AND s.id_subject=?);")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                subject = createSubjetcs(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }

    @Override
    public void add(SubjectDto subjectDto) {
        String sql;
        Subject subject = SubjectMapper.mapFrom(subjectDto);

        if (subject.getId() != null && subject.getId()>0 ) {
            sql = "UPDATE subjects SET name=?,id_teacher=? WHERE id=?";
        }else {
            sql = "INSERT INTO subjects (name,id_teacher) VALUES (?,?);";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, subject.getName());
            stmt.setLong(2, subject.getTeacher().getId());

            if (subject.getId() != null && subject.getId()>0){
                stmt.setLong(3, subject.getId());
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM subjects WHERE id =?")){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
