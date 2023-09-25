package com.example.notasjakarta.services.impl;

import com.example.notasjakarta.domain.model.Teacher;
import com.example.notasjakarta.mapping.dtos.TeacherDto;
import com.example.notasjakarta.repositories.impl.TeacherRepositoryLogicImpl;
import com.example.notasjakarta.services.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepositoryLogicImpl repository;

    public TeacherServiceImpl(TeacherRepositoryLogicImpl repository) {
        this.repository = repository;
    }
    @Override
    public List<TeacherDto> list() {
        return repository.list();
    }

    @Override
    public Teacher byId(Long id) {
        return repository.byId(id);
    }

    @Override
    public void add(Teacher t) {
        repository.add(t);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
