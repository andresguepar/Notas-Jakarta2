package com.example.notasjakarta.repositories.impl;

import com.example.notasjakarta.domain.model.Subject;
import com.example.notasjakarta.domain.model.Teacher;
import com.example.notasjakarta.exceptions.UniversityException;
import com.example.notasjakarta.mapping.dtos.SubjectDto;
import com.example.notasjakarta.mapping.mapper.SubjectMapper;
import com.example.notasjakarta.repositories.SubjectRepository;

import java.util.ArrayList;
import java.util.List;

public class SubjectRepositoryLogicImpl implements SubjectRepository<Subject> {
    private final List<Subject> subjects;

    public SubjectRepositoryLogicImpl() {
        Subject s1 = new Subject(1L,"Art",new Teacher());
        Subject s2 = new Subject(2L,"Maths",new Teacher() );
        Subject s3 = new Subject(3L,"Spanish",new Teacher());
        subjects = new ArrayList<>(List.of(s1, s2, s3));
    }

    @Override
    public List<SubjectDto> list() {
        return SubjectMapper.mapFromDto(subjects);
    }

    @Override
    public Subject byId(Long id) {
        return subjects.stream()
                .filter(e->id.equals(e.getId()))
                .findFirst()
                .orElseThrow(()-> new UniversityException("Student not found"));
    }

    @Override
    public void add(Subject subject) {
        subjects.add(subject);
    }

    @Override
    public void delete(Long id) {
        subjects.removeIf(e->e.getId().equals(id));
    }
}
