package com.example.notasjakarta.controllers.subject;

import com.example.notasjakarta.mapping.dtos.SubjectDto;
import com.example.notasjakarta.repository.impl.SubjectRepositoryImpl;
import com.example.notasjakarta.services.SubjectService;
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
public class SubjectById extends HttpServlet {
    private SubjectRepositoryImpl repository;
    private SubjectService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");
        repository = new SubjectRepositoryImpl(conn);
        service = new SubjectServiceImpl(conn);
        ServletInputStream JsonStream = req.getInputStream();

        ObjectMapper mapper = new ObjectMapper();

        SubjectService service = new SubjectServiceImpl(conn);

        SubjectDto subject = mapper.readValue(JsonStream, SubjectDto.class);
        Long id = subject.id();

        service.byId(id);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Students</h1>");
        out.println(service.byId(id));
        out.println("</body></html>");


    }
}
