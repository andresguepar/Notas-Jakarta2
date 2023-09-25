package com.example.notasjakarta.repositories;

import com.example.notasjakarta.domain.model.Teacher;
import com.example.notasjakarta.mapping.dtos.TeacherDto;

import java.util.List;

public interface TeacherRepository<T>{
    List<TeacherDto> list();

    T byId(Long id);

    void add(Teacher teacher);

    void delete(Long id);
}
