package com.example.notasjakarta.services;


import com.example.notasjakarta.mapping.dtos.StudentDto;
import com.example.notasjakarta.singleDomain.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface StudentService {
    default Connection getConnection() throws SQLException, ClassNotFoundException {
        return ConnectionDB.getInstance();
    }
    List<StudentDto> list();

    StudentDto byId(Long id);

    void add(StudentDto t);

    void delete(Long id);
}
