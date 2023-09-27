package com.example.notasjakarta.mapping.mapper;


import com.example.notasjakarta.domain.model.Grade;
import com.example.notasjakarta.mapping.dtos.GradeDto;

import java.util.List;

public class GradeMapper {
    public static GradeDto mapFrom(Grade source){
        return new GradeDto(source.getId(),
                source.getStudent(),
                source.getSubject(),
                source.getGrade());
    }

    public static Grade mapFrom(GradeDto source){
        return new Grade(source.id(),
                source.student(),
                source.subject(),
                source.grade());
    }

    public static List<Grade> mapFrom(List<GradeDto> source){
        return source.parallelStream()
                .map(GradeMapper::mapFrom)
                .toList();

    }
    public static List<GradeDto> mapFromDto(List<Grade> source){
        return source.parallelStream()
                .map(GradeMapper::mapFrom)
                .toList();

    }
}
