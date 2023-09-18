package com.example.notasjakarta.mapping.dtos;

import com.example.notasjakarta.domain.model.Teacher;

public record SubjectDto(long id,
                         String name,
                         Teacher teacher) {
}
