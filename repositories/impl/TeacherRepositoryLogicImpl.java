package com.example.notasjakarta.repositories.impl;

import com.example.notasjakarta.domain.model.Teacher;
import com.example.notasjakarta.exceptions.UniversityException;
import com.example.notasjakarta.repositories.Repository;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepositoryLogicImpl implements Repository<Teacher> {
    private List<Teacher> teachers;

    public TeacherRepositoryLogicImpl() {
        Teacher t1 = new Teacher(1L,"Tulipiaqwq","tuli@email.com");
        Teacher t2 = new Teacher(2L,"David","david@email.com" );
        Teacher t3 = new Teacher(3L,"Susfhfhtfhana","sus@email.com");
        teachers = new ArrayList<>(List.of(t1, t2, t3));
    }

    @Override
    public List<Teacher> listar() {
        return teachers;
    }

    @Override
    public Teacher porId(Long id) {
        return teachers.stream()
                .filter(e->id.equals(e.getId()))
                .findFirst()
                .orElseThrow(()-> new UniversityException("Student not found"));
    }

    @Override
    public void guardar(Teacher teacher) {
        teachers.add(teacher);
    }

    @Override
    public void eliminar(Long id) {
        teachers.removeIf(e->e.getId().equals(id));
    }
}

