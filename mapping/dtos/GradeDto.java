package com.example.notasjakarta.mapping.dtos;


import com.example.notasjakarta.domain.model.Student;
import com.example.notasjakarta.domain.model.Subject;

public record GradeDto(Long id,
                       Student student,
                       Subject subject,
                       Double grade) {
}
