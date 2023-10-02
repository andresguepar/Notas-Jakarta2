package com.example.notasjakarta.controllers.subject;

import com.example.notasjakarta.domain.model.Subject;
import com.example.notasjakarta.mapping.dtos.SubjectDto;
import com.example.notasjakarta.mapping.mapper.SubjectMapper;
import com.example.notasjakarta.mapping.mapper.TeacherMapper;
import com.example.notasjakarta.repository.impl.SubjectRepositoryImpl;
import com.example.notasjakarta.services.SubjectService;
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

@WebServlet(name = "subjectController", value = "/subject-form")
public class SubjectController extends HttpServlet {

    private SubjectRepositoryImpl repository;
    private SubjectService service;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        Connection conn = (Connection) request.getAttribute("conn");
        SubjectService service = new SubjectServiceImpl(conn);


        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Subjects</h1>");
        out.println(service.list());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        Connection conn = (Connection) req.getAttribute("conn");

        repository = new SubjectRepositoryImpl(conn);
        service = new SubjectServiceImpl(conn);
        TeacherServiceImpl teacherService = new TeacherServiceImpl(conn);

        /*ServletInputStream JsonStream = req.getInputStream();

        ObjectMapper mapper = new ObjectMapper();
        SubjectDto subject = mapper.readValue(JsonStream, SubjectDto.class);*/

        SubjectService service = new SubjectServiceImpl(conn);

        String name = req.getParameter("name");
        Long id = Long.valueOf(req.getParameter("id_teacher"));
        Subject subject = Subject.builder()
                .name(name)
                .teacher(TeacherMapper.mapFrom(teacherService.byId(id)))
                .build();

        SubjectDto subjectDto = SubjectMapper.mapFrom(subject);

        service.add(subjectDto);

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
            out.println("            <li>Name: " + subjectDto.name() + "</li>");
            out.println("            <li>Teacher Id: " + subjectDto.teacher().getId() + "</li>");
            out.println("            <li>Teacher Name: " + subjectDto.teacher().getName() + "</li>");
            out.println("            <li>Teacher Name: " + subjectDto.teacher().getEmail() + "</li>");
            out.println("        </ul>");
            out.println("    </body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");

        repository = new SubjectRepositoryImpl(conn);
        service = new SubjectServiceImpl(conn);
        TeacherServiceImpl teacherService = new TeacherServiceImpl(conn);

        ServletInputStream JsonStream = req.getInputStream();

        ObjectMapper mapper = new ObjectMapper();
        SubjectDto subject = mapper.readValue(JsonStream, SubjectDto.class);

        SubjectService service = new SubjectServiceImpl(conn);


        /*Long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        Long idT = Long.valueOf(req.getParameter("id_teacher"));
        Subject subject = Subject.builder()
                .id(id)
                .name(name)
                .teacher(TeacherMapper.mapFrom(teacherService.byId(idT)))
                .build();

        SubjectDto subjectDto = SubjectMapper.mapFrom(subject);*/

        service.add(subject);

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
            out.println("            <li>Id: " + subject.id() + "</li>");
            out.println("            <li>Name: " + subject.name() + "</li>");
            out.println("            <li>Teacher Id: " + subject.teacher().getId() + "</li>");
            out.println("            <li>Teacher Name: " + subject.teacher().getName() + "</li>");
            out.println("            <li>Teacher Name: " + subject.teacher().getEmail() + "</li>");
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

        SubjectService service = new SubjectServiceImpl(conn);

        SubjectDto subject = mapper.readValue(JsonStream, SubjectDto.class);
        Long id = subject.id();

        service.delete(id);

    }
}
