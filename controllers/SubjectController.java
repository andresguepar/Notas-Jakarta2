package com.example.notasjakarta.controllers;

import com.example.notasjakarta.repositories.impl.SubjectRepositoryLogicImpl;
import com.example.notasjakarta.services.SubjectService;
import com.example.notasjakarta.services.impl.SubjectServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "subjectController", value = "/subject-form")
public class SubjectController extends HttpServlet {

    private SubjectRepositoryLogicImpl subjectRepository;
    private SubjectService service;

    public SubjectController() {
        subjectRepository = new SubjectRepositoryLogicImpl();
        service = new SubjectServiceImpl(subjectRepository);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Students</h1>");
        out.println(service.list());
        out.println("</body></html>");
    }
}
