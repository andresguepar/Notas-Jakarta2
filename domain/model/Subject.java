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
public class Subject implements Serializable {
    private Long id;
    private String name;
    private Teacher teacher;
}
