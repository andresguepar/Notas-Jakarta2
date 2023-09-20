package com.example.notasjakarta.controllers;

import com.example.notasjakarta.domain.model.Student;
import com.example.notasjakarta.exceptions.UniversityException;
import com.example.notasjakarta.repositories.impl.StudentRepositoryLogicImpl;
import com.example.notasjakarta.services.StudentService;
import com.example.notasjakarta.services.impl.StudentServiceImpl;
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

  /*  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Students</h1>");
        out.println(service.porId());
        out.println("</body></html>");
    }*/
}
