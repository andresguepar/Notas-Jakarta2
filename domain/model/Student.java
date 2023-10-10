package com.example.notasjakarta.domain.model;

import jakarta.enterprise.context.SessionScoped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@SessionScoped
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Student implements Serializable {

    private Long id;
    private String name;
    private String email;
    private String semester;
}
