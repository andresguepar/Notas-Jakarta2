package com.example.notasjakarta.controllers;

import com.example.notasjakarta.domain.model.Student;
import com.example.notasjakarta.exceptions.UniversityException;
import com.example.notasjakarta.repositories.impl.StudentRepositoryLogicImpl;
import com.example.notasjakarta.services.StudentService;
import com.example.notasjakarta.services.impl.StudentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "id", value = "/id")
public class Id extends HttpServlet {
    private StudentRepositoryLogicImpl studentRepository;
    private StudentService service;

    public Id() {
        studentRepository = new StudentRepositoryLogicImpl();
        service = new StudentServiceImpl(studentRepository);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        Long id = Long.valueOf(req.getParameter("id"));

        try {
            Student student = service.porId(id);

            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Id correcto</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Id correcto!</h1>");
                out.println(" <h3>Hola " + student.getName() + " has iniciado sesión con éxito!</h3>");
                out.println(" </body>");
                out.println("</html>");

            }

        } catch (UniversityException e) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado " +
                    "para ingresar a esta página!");

        }
    }
}
