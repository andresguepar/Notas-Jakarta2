package com.example.notasjakarta.controllers.teacher;

import com.example.notasjakarta.domain.model.Subject;
import com.example.notasjakarta.domain.model.Teacher;
import com.example.notasjakarta.mapping.dtos.StudentDto;
import com.example.notasjakarta.mapping.dtos.SubjectDto;
import com.example.notasjakarta.mapping.dtos.TeacherDto;
import com.example.notasjakarta.mapping.mapper.SubjectMapper;
import com.example.notasjakarta.mapping.mapper.TeacherMapper;
import com.example.notasjakarta.repository.impl.StudentRepositoryImpl;
import com.example.notasjakarta.repository.impl.SubjectRepositoryImpl;
import com.example.notasjakarta.repository.impl.TeacherRepositoryImpl;
import com.example.notasjakarta.services.StudentService;
import com.example.notasjakarta.services.SubjectService;
import com.example.notasjakarta.services.TeacherService;
import com.example.notasjakarta.services.impl.StudentServiceImpl;
import com.example.notasjakarta.services.impl.SubjectServiceImpl;
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

@WebServlet(name = "teacherController", value = "/teacher-form")
public class TeacherController extends HttpServlet {

    private TeacherRepositoryImpl repository;
    private TeacherService service;


    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");


        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Teachers</h1>");
        out.println(service.list());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");
        repository = new TeacherRepositoryImpl(conn);
        service = new TeacherServiceImpl(conn);

        String name = req.getParameter("name");
        String email = req.getParameter("email");

        Teacher teacher = Teacher.builder()
                .name(name)
                .email(email)
                .build();

        TeacherDto teacherDto = TeacherMapper.mapFrom(teacher);
        service.add(teacherDto);
        System.out.println(service.list());

        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Resultado form</title>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <h1>Resultado form!</h1>");

            out.println("        <ul>");
            out.println("            <li>Name: " + name + "</li>");
            out.println("            <li>Email: " + email + "</li>");
            out.println("        </ul>");
            out.println("    </body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");

        repository = new TeacherRepositoryImpl(conn);
        service = new TeacherServiceImpl(conn);

        ServletInputStream JsonStream = req.getInputStream();

        ObjectMapper mapper = new ObjectMapper();
        TeacherDto teacher = mapper.readValue(JsonStream, TeacherDto.class);

        TeacherService service = new TeacherServiceImpl(conn);


            /* Long id = Long.valueOf(req.getParameter("id"));

        String name = req.getParameter("name");
        String email = req.getParameter("email");

        Teacher teacher = Teacher.builder().id(id)
                .name(name)
                .email(email)
                .build();

        TeacherDto teacherDto = TeacherMapper.mapFrom(teacher);*/
        service.add(teacher);
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Resultado form</title>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <h1>Resultado form!</h1>");

            out.println("        <ul>");
            out.println("            <li>Name: " + teacher.id() + "</li>");
            out.println("            <li>Name: " + teacher.name() + "</li>");
            out.println("            <li>Email: " + teacher.email() + "</li>");
            out.println("        </ul>");
            out.println("    </body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");
        ServletInputStream JsonStream = req.getInputStream();

        ObjectMapper mapper = new ObjectMapper();

        TeacherService service = new TeacherServiceImpl(conn);

        TeacherDto teacher = mapper.readValue(JsonStream, TeacherDto.class);
        Long id = teacher.id();

        service.delete(id);

    }

    public void destroy() {
    }
}
