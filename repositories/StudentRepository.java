package com.example.notasjakarta.repositories;

import com.example.notasjakarta.mapping.dtos.StudentDto;

import java.util.List;

public interface StudentRepository<T>{
    List<StudentDto> listar();

    T porId(Long id);

    void guardar(T t);

    void eliminar(Long id);
}
