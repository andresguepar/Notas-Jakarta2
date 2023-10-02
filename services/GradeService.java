package com.example.notasjakarta.services;

import com.example.notasjakarta.mapping.dtos.GradeDto;
import com.example.notasjakarta.singleDomain.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GradeService {
    default Connection getConnection() throws SQLException, ClassNotFoundException {
        return ConnectionDB.getInstance();
    }
    List<GradeDto> list();

    GradeDto byId(Long id);

    void add(GradeDto t);

    void delete(Long id);
}
