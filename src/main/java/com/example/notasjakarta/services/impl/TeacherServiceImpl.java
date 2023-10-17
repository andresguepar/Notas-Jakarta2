package com.example.notasjakarta.services.impl;

import com.example.notasjakarta.mapping.dtos.TeacherDto;
import com.example.notasjakarta.repository.Repository;
import com.example.notasjakarta.services.TeacherService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
@ApplicationScoped
public class TeacherServiceImpl implements TeacherService {
    @Inject
    private Repository<TeacherDto> repository;

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
