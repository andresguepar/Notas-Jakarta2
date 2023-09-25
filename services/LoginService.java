package com.example.notasjakarta.services;

import com.example.notasjakarta.singleDomain.ConnectionDB;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface LoginService {
    default Connection getConnection() throws SQLException, ClassNotFoundException {
        return ConnectionDB.getInstance();
    }
    Optional<String> getUsername(HttpServletRequest request);
}
