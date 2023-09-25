package com.example.notasjakarta.services;

import com.example.notasjakarta.domain.model.Subject;
import com.example.notasjakarta.mapping.dtos.SubjectDto;

import java.util.List;

public interface SubjectService {
    List<SubjectDto> list();

    Subject byId(Long id);

    void add(Subject t);

    void delete(Long id);
}
