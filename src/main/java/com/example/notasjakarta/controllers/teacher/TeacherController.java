package com.example.notasjakarta.controllers.teacher;

import com.example.notasjakarta.domain.model.Teacher;
import com.example.notasjakarta.mapping.dtos.TeacherDto;
import com.example.notasjakarta.mapping.mapper.TeacherMapper;
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
import java.util.Map;

@WebServlet(name = "teacherController", value = "/teacher-form")
public class TeacherController extends HttpServlet {

    @Inject
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

        String name = req.getParameter("name");
        String email = req.getParameter("email");

        Teacher teacher = Teacher.builder()
                .name(name)
                .email(email)
                .build();

        TeacherDto teacherDto = TeacherMapper.mapFrom(teacher);
        Map<String,String> errorsmap= getErrors(name,email);

        if(errorsmap.isEmpty()) {
            try (PrintWriter out = resp.getWriter()) {
                service.add(teacherDto);
                System.out.println(service.list());
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
        }else{
            req.setAttribute("errorsmap", errorsmap);
            getServletContext().getRequestDispatcher("/student.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        ServletInputStream JsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        TeacherDto teacher = mapper.readValue(JsonStream, TeacherDto.class);

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

        ServletInputStream JsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();

        TeacherDto teacher = mapper.readValue(JsonStream, TeacherDto.class);
        Long id = teacher.id();

        service.delete(id);

    }
    private Map<String,String> getErrors(String name, String email){
        Map<String,String> errors = new HashMap<>();
        if(name==null ||name.isBlank()){
            errors.put("name","El nombre es requerido");
        }
        if(email==null ||email.isBlank()){
            errors.put("email","El email es requerido");
        }
        return errors;
    }
    public void destroy() {
    }
}
