package com.example.notasjakarta.controllers.grade;

import com.example.notasjakarta.mapping.dtos.GradeDto;
import com.example.notasjakarta.mapping.dtos.SubjectDto;
import com.example.notasjakarta.repository.impl.GradeRepositoryImpl;
import com.example.notasjakarta.repository.impl.SubjectRepositoryImpl;
import com.example.notasjakarta.services.GradeService;
import com.example.notasjakarta.services.SubjectService;
import com.example.notasjakarta.services.impl.GradeServiceImpl;
import com.example.notasjakarta.services.impl.SubjectServiceImpl;
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

@WebServlet(name = "subjectById", value = "/subject-formById")
public class GradeById extends HttpServlet {
    private GradeRepositoryImpl repository;
    private GradeService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");
        repository = new GradeRepositoryImpl(conn);
        service = new GradeServiceImpl(conn);
        ServletInputStream JsonStream = req.getInputStream();

        ObjectMapper mapper = new ObjectMapper();

        GradeService service = new GradeServiceImpl(conn);

        GradeDto grade = mapper.readValue(JsonStream, GradeDto.class);
        Long id = grade.id();

        service.byId(id);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Students</h1>");
        out.println(service.byId(id));
        out.println("</body></html>");


    }
}
