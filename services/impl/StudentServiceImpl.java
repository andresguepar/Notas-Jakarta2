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
    public List<StudentDto> listar() {
        return repository.listar();
    }

    @Override
    public Student porId(Long id) {
        return repository.porId(id);
    }

    @Override
    public void guardar(Student t) {
        repository.guardar(t);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }
}
