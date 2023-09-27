package com.example.notasjakarta.services.impl;

import com.example.notasjakarta.domain.model.Grade;
import com.example.notasjakarta.mapping.dtos.GradeDto;
import com.example.notasjakarta.repository.impl.GradeRepositoryImpl;
import com.example.notasjakarta.services.GradeService;

import java.sql.Connection;
import java.util.List;

public class GradeServiceImpl implements GradeService {
    private final GradeRepositoryImpl repository;

    public GradeServiceImpl(Connection connection) {
        this.repository = new GradeRepositoryImpl(connection);
    }
    @Override
    public List<GradeDto> list() {
        return repository.list();
    }

    @Override
    public GradeDto byId(Long id) {
        return repository.byId(id);
    }

    @Override
    public void add(GradeDto t) {
        repository.add(t);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
