package com.example.notasjakarta.services.impl;

import com.example.notasjakarta.domain.model.Subject;
import com.example.notasjakarta.mapping.dtos.SubjectDto;
import com.example.notasjakarta.repositories.impl.SubjectRepositoryLogicImpl;
import com.example.notasjakarta.services.SubjectService;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepositoryLogicImpl repository;

    public SubjectServiceImpl(SubjectRepositoryLogicImpl repository) {
        this.repository = repository;
    }
    @Override
    public List<SubjectDto> list() {
        return repository.list();
    }

    @Override
    public Subject byId(Long id) {
        return repository.byId(id);
    }

    @Override
    public void add(Subject t) {
        repository.add(t);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
