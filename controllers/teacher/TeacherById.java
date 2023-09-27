package com.example.notasjakarta.controllers.teacher;

import com.example.notasjakarta.domain.model.Teacher;
import com.example.notasjakarta.mapping.dtos.StudentDto;
import com.example.notasjakarta.mapping.dtos.TeacherDto;
import com.example.notasjakarta.repository.impl.StudentRepositoryImpl;
import com.example.notasjakarta.repository.impl.TeacherRepositoryImpl;
import com.example.notasjakarta.services.StudentService;
import com.example.notasjakarta.services.TeacherService;
import com.example.notasjakarta.services.impl.StudentServiceImpl;
import com.example.notasjakarta.services.impl.TeacherServiceImpl;
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

@WebServlet(name = "teacherById", value = "/teacher-formById")
public class TeacherById extends HttpServlet {
    private TeacherRepositoryImpl repository;
    private TeacherService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");
        repository = new TeacherRepositoryImpl(conn);
        service = new TeacherServiceImpl(conn);
        ServletInputStream JsonStream = req.getInputStream();

        ObjectMapper mapper = new ObjectMapper();

        TeacherService service = new TeacherServiceImpl(conn);

        TeacherDto teacher = mapper.readValue(JsonStream, TeacherDto.class);
        Long id = teacher.id();

        service.byId(id);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Students</h1>");
        out.println(service.byId(id));
        out.println("</body></html>");


    }
}
