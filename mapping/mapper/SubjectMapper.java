package com.example.notasjakarta.mapping.mapper;



import com.example.notasjakarta.domain.model.Subject;
import com.example.notasjakarta.mapping.dtos.SubjectDto;

import java.util.List;

public class SubjectMapper {
    public static SubjectDto mapFrom(Subject source){
        return new SubjectDto(source.getId(),
                source.getName(),
                source.getTeacher());
    }

    public static Subject mapFrom(SubjectDto source){
        return new Subject(source.id(),
                source.name(),
                source.teacher());
    }

    public static List<Subject> mapFrom(List<SubjectDto> source){
        return source.parallelStream()
                .map(SubjectMapper::mapFrom)
                .toList();

    }
    public static List<SubjectDto> mapFromDto(List<Subject> source){
        return source.parallelStream()
                .map(SubjectMapper::mapFrom)
                .toList();

    }
}
