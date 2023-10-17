package com.example.notasjakarta.services.impl;

import com.example.notasjakarta.mapping.dtos.SubjectDto;
import com.example.notasjakarta.repository.Repository;
import com.example.notasjakarta.services.SubjectService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
@ApplicationScoped
public class SubjectServiceImpl implements SubjectService {
    @Inject
    private Repository<SubjectDto> repository;
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
