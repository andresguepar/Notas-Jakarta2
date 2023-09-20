package com.example.notasjakarta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Student {

    private Long id;
    private String name;
    private String email;
    private String semester;
}
