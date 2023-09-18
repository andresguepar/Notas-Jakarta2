package com.example.notasjakarta.services;

import com.example.notasjakarta.domain.model.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> listar();

    Teacher porId(Long id);

    void guardar(Teacher t);

    void eliminar(Long id);
}
