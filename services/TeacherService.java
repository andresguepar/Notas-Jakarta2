package com.example.notasjakarta.services;

import com.example.notasjakarta.mapping.dtos.TeacherDto;
import com.example.notasjakarta.singleDomain.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface TeacherService {
    default Connection getConnection() throws SQLException, ClassNotFoundException {
        return ConnectionDB.getInstance();
    }
    List<TeacherDto> list();

    TeacherDto byId(Long id);

    void add(TeacherDto t);

    void delete(Long id);
}
