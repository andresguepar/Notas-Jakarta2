package com.example.notasjakarta.services;

import com.example.notasjakarta.mapping.dtos.SubjectDto;

import java.util.List;

public interface SubjectService {

    List<SubjectDto> list();

    SubjectDto byId(Long id);

    void add(SubjectDto t);

    void delete(Long id);
}
