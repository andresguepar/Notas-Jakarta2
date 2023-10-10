package com.example.notasjakarta.services;

import com.example.notasjakarta.mapping.dtos.GradeDto;

import java.util.List;

public interface GradeService {
    List<GradeDto> list();

    GradeDto byId(Long id);

    void add(GradeDto t);

    void delete(Long id);
}
