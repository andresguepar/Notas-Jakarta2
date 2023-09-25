package com.example.notasjakarta.services.impl;

import com.example.notasjakarta.mapping.dtos.SubjectDto;
import com.example.notasjakarta.repository.impl.SubjectRepositoryImpl;
import com.example.notasjakarta.services.SubjectService;

import java.sql.Connection;
import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepositoryImpl repository;

    public SubjectServiceImpl(Connection connection) {
        this.repository = new SubjectRepositoryImpl(connection);
    }
    @Override
    public List<SubjectDto> list() {
        return repository.list();
    }

    @Override
    public SubjectDto byId(Long id) {
        return repository.byId(id);
    }

    @Override
    public void add(SubjectDto t) {
        repository.add(t);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
