package com.example.notasjakarta.repositories;

import com.example.notasjakarta.mapping.dtos.StudentDto;

import java.util.List;

public interface StudentRepository<T>{
    List<StudentDto> list();

    T byId(Long id);

    void add(T t);

    void delete(Long id);
}
