package com.example.notasjakarta.services;

import com.example.notasjakarta.domain.model.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> listar();

    Subject porId(Long id);

    void guardar(Subject t);

    void eliminar(Long id);
}
