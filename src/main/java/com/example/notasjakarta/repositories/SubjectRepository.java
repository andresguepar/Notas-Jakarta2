package com.example.notasjakarta.repositories;

import com.example.notasjakarta.domain.model.Subject;
import com.example.notasjakarta.mapping.dtos.SubjectDto;

import java.util.List;

public interface SubjectRepository<T>{
    List<SubjectDto> list();

    T byId(Long id);

    void add(Subject t);

    void delete(Long id);
}
