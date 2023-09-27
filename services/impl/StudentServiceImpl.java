package com.example.notasjakarta.services.impl;


import com.example.notasjakarta.mapping.dtos.StudentDto;
import com.example.notasjakarta.repository.impl.StudentRepositoryImpl;
import com.example.notasjakarta.services.StudentService;

import java.sql.Connection;
import java.util.List;
public class StudentServiceImpl implements StudentService {
    private StudentRepositoryImpl studentRepository;

    public StudentServiceImpl(Connection connection) {
        this.studentRepository = new StudentRepositoryImpl(connection);
    }

    @Override
    public List<StudentDto> list() {
        return studentRepository.list();
    }

    @Override
    public StudentDto byId(Long id) {
        return studentRepository.byId(id);
    }

    @Override
    public void add(StudentDto t) {
        studentRepository.add(t);
    }

    @Override
    public void delete(Long id) {
        studentRepository.delete(id);

    }
}
