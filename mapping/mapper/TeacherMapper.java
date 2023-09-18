package com.example.notasjakarta.mapping.mapper;



import com.example.notasjakarta.domain.model.Teacher;
import com.example.notasjakarta.mapping.dtos.TeacherDto;

import java.util.List;

public class TeacherMapper {
    public static TeacherDto mapFrom(Teacher source){
        return new TeacherDto(source.getId(),
                source.getName(),
                source.getEmail());
    }

    public static Teacher mapFrom(TeacherDto source){
        return new Teacher(source.id(),
                source.name(),
                source.email());
    }

    public static List<Teacher> mapFrom(List<TeacherDto> source){
        return source.parallelStream()
                .map(TeacherMapper::mapFrom)
                .toList();

    }
    public static List<TeacherDto> mapFromDto(List<Teacher> source){
        return source.parallelStream()
                .map(TeacherMapper::mapFrom)
                .toList();

    }
}
