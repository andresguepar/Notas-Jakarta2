package com.example.notasjakarta.repositories.impl;

import com.example.notasjakarta.domain.model.Subject;
import com.example.notasjakarta.domain.model.Teacher;
import com.example.notasjakarta.exceptions.UniversityException;
import com.example.notasjakarta.repositories.Repository;

import java.util.ArrayList;
import java.util.List;

public class SubjectRepositoryLogicImpl implements Repository<Subject> {
    private List<Subject> subjects;

    public SubjectRepositoryLogicImpl() {
        Subject s1 = new Subject(1L,"Art",new Teacher());
        Subject s2 = new Subject(2L,"Maths",new Teacher() );
        Subject s3 = new Subject(3L,"Spanish",new Teacher());
        subjects = new ArrayList<>(List.of(s1, s2, s3));
    }

    @Override
    public List<Subject> listar() {
        return subjects;
    }

    @Override
    public Subject porId(Long id) {
        return subjects.stream()
                .filter(e->id.equals(e.getId()))
                .findFirst()
                .orElseThrow(()-> new UniversityException("Student not found"));
    }

    @Override
    public void guardar(Subject subject) {
        subjects.add(subject);
    }

    @Override
    public void eliminar(Long id) {
        subjects.removeIf(e->e.getId().equals(id));
    }
}
