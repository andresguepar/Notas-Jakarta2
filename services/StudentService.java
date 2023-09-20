package com.example.notasjakarta.services;


import com.example.notasjakarta.domain.model.Student;
import com.example.notasjakarta.mapping.dtos.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> listar();

    Student porId(Long id);

    void guardar(Student t);

    void eliminar(Long id);
}
