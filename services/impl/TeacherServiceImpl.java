package com.example.notasjakarta.services.impl;

import com.example.notasjakarta.mapping.dtos.TeacherDto;
import com.example.notasjakarta.repository.impl.TeacherRepositoryImpl;
import com.example.notasjakarta.services.TeacherService;

import java.sql.Connection;
import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepositoryImpl repository;

    public TeacherServiceImpl(Connection connection) {
        this.repository = new TeacherRepositoryImpl(connection);
    }
    @Override
    public List<TeacherDto> list() {
        return repository.list();
    }

    @Override
    public TeacherDto byId(Long id) {
        return repository.byId(id);
    }

    @Override
    public void add(TeacherDto t) {
        repository.add(t);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
