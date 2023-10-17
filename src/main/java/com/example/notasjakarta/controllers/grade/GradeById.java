package com.example.notasjakarta.controllers.grade;

import com.example.notasjakarta.mapping.dtos.GradeDto;
import com.example.notasjakarta.services.GradeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "subjectById", value = "/subject-formById")
public class GradeById extends HttpServlet {
    @Inject
    private GradeService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        ServletInputStream JsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();

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
