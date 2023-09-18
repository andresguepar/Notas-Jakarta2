package com.example.notasjakarta.repositories.impl;


import com.example.notasjakarta.domain.model.Student;
import com.example.notasjakarta.exceptions.UniversityException;
import com.example.notasjakarta.repositories.Repository;

import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryLogicImpl implements Repository<Student> {
    private List<Student> students;

    public StudentRepositoryLogicImpl() {
        Student s1 = new Student(1L,"Lexa","lexa@email.com","1" );
        Student s2 = new Student(2L,"Lulu","lulu@email.com","3" );
        Student s3 = new Student(3L,"Salmon","sal@email.com","2" );
        students = new ArrayList<>(List.of(s1, s2, s3));
    }

    @Override
    public List<Student> listar() {
        return students;
    }

    @Override
    public Student porId(Long id) {
        return students.stream()
                .filter(e->id.equals(e.getId()))
                .findFirst()
                .orElseThrow(()-> new UniversityException("Student not found"));
    }

    @Override
    public void guardar(Student student) {
        students.add(student);
    }

    @Override
    public void eliminar(Long id) {
        students.removeIf(e->e.getId().equals(id));
    }
}
