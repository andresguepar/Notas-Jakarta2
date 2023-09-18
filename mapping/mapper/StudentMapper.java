package com.example.notasjakarta.mapping.mapper;


import com.example.notasjakarta.domain.model.Student;
import com.example.notasjakarta.mapping.dtos.StudentDto;

import java.util.List;

public class StudentMapper {
    public static StudentDto mapFrom(Student source){
        return new StudentDto(source.getId(),
                source.getName(),
                source.getEmail(),
                source.getSemestre()
        );
    }

    public static Student mapFrom(StudentDto source){
        return new Student(source.id(),
                source.name(),
                source.email(),
                source.semestre());
    }

    public static List<Student> mapFrom(List<StudentDto> source){
        return source.parallelStream()
                .map(StudentMapper::mapFrom)
                .toList();

    }
    public static List<StudentDto> mapFromDto(List<Student> source){
        return source.parallelStream()
                .map(StudentMapper::mapFrom)
                .toList();

    }
}
