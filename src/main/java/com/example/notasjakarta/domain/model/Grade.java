package com.example.notasjakarta.domain.model;

import jakarta.enterprise.context.SessionScoped;
import lombok.*;

import java.io.Serializable;

@SessionScoped
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Grade implements Serializable {
    private Long id;
    private Student student;
    private Subject subject;
    private Double grade;
}
