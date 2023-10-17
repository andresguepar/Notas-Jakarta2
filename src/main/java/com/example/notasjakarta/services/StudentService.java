package com.example.notasjakarta.services;


import com.example.notasjakarta.mapping.dtos.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> list();

    StudentDto byId(Long id);

    void add(StudentDto t);

    void delete(Long id);
}
