package com.example.notasjakarta.services.impl;


import com.example.notasjakarta.domain.model.Student;
import com.example.notasjakarta.mapping.dtos.StudentDto;
import com.example.notasjakarta.repositories.impl.StudentRepositoryLogicImpl;
import com.example.notasjakarta.services.StudentService;

import java.util.List;
public class StudentServiceImpl implements StudentService {

    private final StudentRepositoryLogicImpl repository;

    public StudentServiceImpl(StudentRepositoryLogicImpl repository) {
        this.repository = repository;
    }
    @Override
    public List<StudentDto> list() {
        return repository.list();
    }

    @Override
    public Student byId(Long id) {
        return repository.byId(id);
    }

    @Override
    public void add(Student t) {
        repository.add(t);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
