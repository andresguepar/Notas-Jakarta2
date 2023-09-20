package com.example.notasjakarta.repositories;

import com.example.notasjakarta.domain.model.Teacher;
import com.example.notasjakarta.mapping.dtos.TeacherDto;

import java.util.List;

public interface TeacherRepository<T>{
    List<TeacherDto> listar();

    T porId(Long id);

    void guardar(Teacher teacher);

    void eliminar(Long id);
}
