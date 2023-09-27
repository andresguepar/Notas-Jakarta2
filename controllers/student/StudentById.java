package com.example.notasjakarta.controllers.student;

import com.example.notasjakarta.mapping.dtos.StudentDto;
import com.example.notasjakarta.repository.impl.StudentRepositoryImpl;
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
import java.sql.Connection;

@WebServlet(name = "studentById", value = "/student-formById")
public class StudentById extends HttpServlet {
    private StudentRepositoryImpl repository;
    private StudentService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");
        repository = new StudentRepositoryImpl(conn);
        service = new StudentServiceImpl(conn);
        ServletInputStream JsonStream = req.getInputStream();

        ObjectMapper mapper = new ObjectMapper();

        StudentService service = new StudentServiceImpl(conn);

        StudentDto student = mapper.readValue(JsonStream, StudentDto.class);
        Long id = student.id();

        service.byId(id);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Students</h1>");
        out.println(service.byId(id));
        out.println("</body></html>");


    }
}
