package com.example.notasjakarta.controllers.grade;

import com.example.notasjakarta.domain.model.Grade;
import com.example.notasjakarta.mapping.dtos.GradeDto;
import com.example.notasjakarta.mapping.mapper.GradeMapper;
import com.example.notasjakarta.mapping.mapper.StudentMapper;
import com.example.notasjakarta.mapping.mapper.SubjectMapper;
import com.example.notasjakarta.repository.impl.GradeRepositoryImpl;
import com.example.notasjakarta.services.GradeService;
import com.example.notasjakarta.services.StudentService;
import com.example.notasjakarta.services.SubjectService;
import com.example.notasjakarta.services.impl.GradeServiceImpl;
import com.example.notasjakarta.services.impl.StudentServiceImpl;
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

@WebServlet(name = "gradeController", value = "/grade-form")
public class GradeController extends HttpServlet {

    private GradeRepositoryImpl repository;
    private GradeService service;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        Connection conn = (Connection) request.getAttribute("conn");
        GradeService service = new GradeServiceImpl(conn);

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Grades</h1>");
        out.println(service.list());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        Connection conn = (Connection) req.getAttribute("conn");

        repository = new GradeRepositoryImpl(conn);
        service = new GradeServiceImpl(conn);

        StudentService studentService = new StudentServiceImpl(conn);
        SubjectService subjectService = new SubjectServiceImpl(conn);

        /*ServletInputStream JsonStream = req.getInputStream();

        ObjectMapper mapper = new ObjectMapper();
        GradeDto grade = mapper.readValue(JsonStream, GradeDto.class);*/
        GradeService service = new GradeServiceImpl(conn);

        Long idSt = Long.valueOf(req.getParameter("id_student"));
        Long idS = Long.valueOf(req.getParameter("id_subject"));
        Double gr = Double.valueOf(req.getParameter("grade"));


        Grade grade = Grade.builder()
                .student(StudentMapper.mapFrom(studentService.byId(idSt)))
                .subject(SubjectMapper.mapFrom(subjectService.byId(idS)))
                .grade(gr)
                .build();

        GradeDto gradeDto = GradeMapper.mapFrom(grade);

        service.add(gradeDto);

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
            out.println("            <li>Student Id: " + gradeDto.student().getId() + "</li>");
            out.println("            <li>Student Name: " + gradeDto.student().getName() + "</li>");
            out.println("            <li>Student Email: " + gradeDto.student().getEmail() + "</li>");
            out.println("            <li>Student Semester: " + gradeDto.student().getSemester() + "</li>");
            out.println("            <li>Subject Id: " + gradeDto.subject().getId() + "</li>");
            out.println("            <li>Subject Name: " + gradeDto.subject().getName() + "</li>");
            out.println("            <li>Teacher Id: " + gradeDto.subject().getTeacher().getId() + "</li>");
            out.println("            <li>Teacher Name: " + gradeDto.subject().getTeacher().getName() + "</li>");
            out.println("            <li>Teacher Email: " + gradeDto.subject().getTeacher().getEmail() + "</li>");
            out.println("            <li>Grade: " + gradeDto.grade() + "</li>");
            out.println("        </ul>");
            out.println("    </body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");

        repository = new GradeRepositoryImpl(conn);
        service = new GradeServiceImpl(conn);

        StudentService studentService = new StudentServiceImpl(conn);
        SubjectService subjectService = new SubjectServiceImpl(conn);

        ServletInputStream JsonStream = req.getInputStream();

        ObjectMapper mapper = new ObjectMapper();
        GradeDto grade = mapper.readValue(JsonStream, GradeDto.class);
        GradeService service = new GradeServiceImpl(conn);

        /*Long id = Long.valueOf(req.getParameter("id"));
        Long idSt = Long.valueOf(req.getParameter("id_student"));
        Long idS = Long.valueOf(req.getParameter("id_subject"));
        Double gr = Double.valueOf(req.getParameter("grade"));

        Grade grade = Grade.builder()
                .id(id)
                .student(StudentMapper.mapFrom(studentService.byId(idSt)))
                .subject(SubjectMapper.mapFrom(subjectService.byId(idS)))
                .grade(gr)
                .build();

        GradeDto gradeDto = GradeMapper.mapFrom(grade);*/

        service.add(grade);

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
            out.println("            <li>Grade Id: " + grade.id() + "</li>");
            out.println("            <li>Student Name: " + grade.student().getId() + "</li>");
            out.println("            <li>Student Name: " + grade.student().getName() + "</li>");
            out.println("            <li>Student Email: " + grade.student().getEmail() + "</li>");
            out.println("            <li>Student Semester: " + grade.student().getSemester() + "</li>");
            out.println("            <li>Subject Id: " + grade.subject().getId() + "</li>");
            out.println("            <li>Subject Name: " + grade.subject().getName() + "</li>");
            out.println("            <li>Teacher Name: " + grade.subject().getTeacher().getName() + "</li>");
            out.println("            <li>Teacher Email: " + grade.subject().getTeacher().getEmail() + "</li>");
            out.println("            <li>Grade Id: " + grade.grade() + "</li>");

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

        GradeDto grade = mapper.readValue(JsonStream, GradeDto.class);
        Long id = grade.id();

        service.delete(id);

    }
}
