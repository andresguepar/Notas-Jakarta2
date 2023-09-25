package com.example.notasjakarta.services;


import com.example.notasjakarta.domain.model.Student;
import com.example.notasjakarta.mapping.dtos.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> list();

    Student byId(Long id);

    void add(Student t);

    void delete(Long id);
}
