package com.example.notasjakarta.services;

import com.example.notasjakarta.mapping.dtos.SubjectDto;
import com.example.notasjakarta.singleDomain.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SubjectService {
    default Connection getConnection() throws SQLException, ClassNotFoundException {
        return ConnectionDB.getInstance();
    }
    List<SubjectDto> list();

    SubjectDto byId(Long id);

    void add(SubjectDto t);

    void delete(Long id);
}
