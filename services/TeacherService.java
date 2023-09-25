package com.example.notasjakarta.services;

import com.example.notasjakarta.domain.model.Teacher;
import com.example.notasjakarta.mapping.dtos.TeacherDto;

import java.util.List;

public interface TeacherService {
    List<TeacherDto> list();

    Teacher byId(Long id);

    void add(Teacher t);

    void delete(Long id);
}
