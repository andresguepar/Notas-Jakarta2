package com.example.notasjakarta.services.impl;


import com.example.notasjakarta.mapping.dtos.StudentDto;
import com.example.notasjakarta.repository.Repository;
import com.example.notasjakarta.services.StudentService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
@ApplicationScoped
public class StudentServiceImpl implements StudentService {
    @Inject
    private Repository<StudentDto> repository;

    @Override
    public List<StudentDto> list() {
        return repository.list();
    }

    @Override
    public StudentDto byId(Long id) {
        return repository.byId(id);
    }

    @Override
    public void add(StudentDto t) {
        repository.add(t);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);

    }
}
