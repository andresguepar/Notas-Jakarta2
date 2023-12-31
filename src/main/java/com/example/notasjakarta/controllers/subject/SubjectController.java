package com.example.notasjakarta.controllers.subject;

import com.example.notasjakarta.domain.model.Subject;
import com.example.notasjakarta.mapping.dtos.SubjectDto;
import com.example.notasjakarta.mapping.dtos.TeacherDto;
import com.example.notasjakarta.mapping.mapper.SubjectMapper;
import com.example.notasjakarta.mapping.mapper.TeacherMapper;
import com.example.notasjakarta.services.SubjectService;
import com.example.notasjakarta.services.TeacherService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "subjectController", value = "/subject-form")
public class SubjectController extends HttpServlet {
    @Inject
    private TeacherService serviceT;
    @Inject
    private SubjectService service;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Subjects</h1>");
        out.println(service.list());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        /*ServletInputStream JsonStream = req.getInputStream();

        ObjectMapper mapper = new ObjectMapper();
        SubjectDto subject = mapper.readValue(JsonStream, SubjectDto.class);*/

        String name = req.getParameter("name");
        String teacherName = req.getParameter("teachers");
        Map<String, String> errorsmap = getErrors(name, teacherName);

        if(errorsmap.isEmpty()) {

            TeacherDto teacherDto = getTeacherByName(teacherName);

            Subject subject = Subject.builder()
                    .name(name)
                    .teacher(TeacherMapper.mapFrom(teacherDto))
                    .build();
            SubjectDto subjectDto = SubjectMapper.mapFrom(subject);

            service.add(subjectDto);
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
                out.println("            <li>Name: " + subjectDto.name() + "</li>");
                out.println("            <li>Teacher Id: " + subjectDto.teacher().getId() + "</li>");
                out.println("            <li>Teacher Name: " + subjectDto.teacher().getName() + "</li>");
                out.println("            <li>Teacher Name: " + subjectDto.teacher().getEmail() + "</li>");
                out.println("        </ul>");
                out.println("    </body>");
                out.println("</html>");
            }
        }else{
            req.setAttribute("errorsmap", errorsmap);
            getServletContext().getRequestDispatcher("/subject.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        ServletInputStream JsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();

        SubjectDto subject = mapper.readValue(JsonStream, SubjectDto.class);

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

        ServletInputStream JsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();

        SubjectDto subject = mapper.readValue(JsonStream, SubjectDto.class);
        Long id = subject.id();

        service.delete(id);

    }
    private TeacherDto getTeacherByName(String name){
        List<TeacherDto> teacher = serviceT.list();
        return teacher .stream()
                .filter(e->e.name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

    }
    private Map<String,String> getErrors(String name, String teacher){
        Map<String,String> errors = new HashMap<>();
        if(name==null || name.isBlank()){
            errors.put("name","El nombre es requerido");
        }
        if(teacher==null || teacher.isBlank() || teacher == "--select--" ){
            errors.put("teacher","El teacher es requerido");
        }
        return errors;
    }
    public void destroy() {
    }
}
