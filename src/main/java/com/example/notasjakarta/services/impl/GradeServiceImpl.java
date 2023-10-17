package com.example.notasjakarta.services.impl;

import com.example.notasjakarta.mapping.dtos.GradeDto;
import com.example.notasjakarta.repository.Repository;
import com.example.notasjakarta.services.GradeService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
@ApplicationScoped

public class GradeServiceImpl implements GradeService {
    @Inject
    private Repository<GradeDto> repository;
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
