package com.example.notasjakarta.controllers;

import com.example.notasjakarta.domain.model.Student;
import com.example.notasjakarta.mapping.dtos.StudentDto;
import com.example.notasjakarta.repositories.impl.StudentRepositoryLogicImpl;
import com.example.notasjakarta.services.StudentService;
import com.example.notasjakarta.services.impl.StudentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "studentJson", value = "/student.json")
public class StudentJson extends HttpServlet {
    private StudentRepositoryLogicImpl studentRepository;
    private StudentService service;

    public StudentJson() {
        studentRepository = new StudentRepositoryLogicImpl();
        service = new StudentServiceImpl(studentRepository);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        ServletInputStream jsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        Student student = mapper.readValue(jsonStream, Student.class);
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println(" <head>");
            out.println(" <meta charset=\"UTF-8\">");
            out.println(" <title>Detalle de estudiante desde json</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println(" <h1>Detalle de estudiante desde json!</h1>");
            out.println("<ul>");
            out.println("<li>Id: " + student.getId() + "</li>");
            out.println("<li>Nombre: " + student.getName() + "</li>");
            out.println("<li>Email: " + student.getEmail() + "</li>");
            out.println("<li>Semestre: " + student.getSemester() + "</li>");

            out.println("</ul>");
            out.println(" </body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        List<StudentDto> students = service.listar();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(students);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }
}
