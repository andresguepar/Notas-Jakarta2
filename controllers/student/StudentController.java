package com.example.notasjakarta.controllers.student;


import com.example.notasjakarta.domain.model.Student;
import com.example.notasjakarta.mapping.dtos.StudentDto;
import com.example.notasjakarta.mapping.mapper.StudentMapper;
import com.example.notasjakarta.repository.impl.StudentRepositoryImpl;
import com.example.notasjakarta.services.StudentService;
import com.example.notasjakarta.services.impl.StudentServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
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

@WebServlet(name = "studentController", value = "/student-form")
public class StudentController extends HttpServlet {


    private String message;
    private StudentRepositoryImpl repository;
    private StudentService service;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        Connection conn = (Connection) request.getAttribute("conn");

        response.setContentType("text/html");
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Students</h1>");
        out.println(service.list());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");
        ServletInputStream JsonStream = req.getInputStream();

        ObjectMapper mapper = new ObjectMapper();
        StudentDto student = mapper.readValue(JsonStream, StudentDto.class);

        StudentService service = new StudentServiceImpl(conn);

        /*String name = req.getParameter("name");
        String email = req.getParameter("email");
        String semester = req.getParameter("semester");

        Student student = Student.builder()
                .name(name)
                .email(email)
                .semester(semester)
                .build();

        StudentDto studentDto = StudentMapper.mapFrom(student);*/
        service.add(student);
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
            out.println("            <li>Name: " + student.name() + "</li>");
            out.println("            <li>Email: " + student.email() + "</li>");
            out.println("            <li>Semester: " + student.semester() + "</li>");
            out.println("        </ul>");
            out.println("    </body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");

        repository = new StudentRepositoryImpl(conn);
        service = new StudentServiceImpl(conn);

        ServletInputStream JsonStream = req.getInputStream();

        ObjectMapper mapper = new ObjectMapper();
        StudentDto student = mapper.readValue(JsonStream, StudentDto.class);

        StudentService service = new StudentServiceImpl(conn);


            /* Long id = Long.valueOf(req.getParameter("id"));
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String semester = req.getParameter("semester");


               Student student = Student.builder()
                        .id(id)
                        .name(name)
                        .email(email)
                        .semester(semester)
                        .build();

                StudentDto studentDto = StudentMapper.mapFrom(student)*/;
                service.add(student);
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
                out.println("            <li>Id: " + student.id() + "</li>");
                out.println("            <li>Name: " + student.name() + "</li>");
                out.println("            <li>Email: " + student.email() + "</li>");
                out.println("            <li>Semester: " + student.semester() + "</li>");
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

        StudentService service = new StudentServiceImpl(conn);

        StudentDto student = mapper.readValue(JsonStream, StudentDto.class);
        Long id = student.id();

        service.delete(id);

    }

    public void destroy() {
    }
}