package com.example.notasjakarta.services;

import com.example.notasjakarta.mapping.dtos.TeacherDto;

import java.util.List;

public interface TeacherService {
    List<TeacherDto> list();

    TeacherDto byId(Long id);

    void add(TeacherDto t);

    void delete(Long id);
}
