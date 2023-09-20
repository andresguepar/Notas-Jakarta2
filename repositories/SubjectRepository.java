package com.example.notasjakarta.repositories;

import com.example.notasjakarta.domain.model.Subject;
import com.example.notasjakarta.mapping.dtos.SubjectDto;

import java.util.List;

public interface SubjectRepository<T>{
    List<SubjectDto> listar();

    T porId(Long id);

    void guardar(Subject t);

    void eliminar(Long id);
}
