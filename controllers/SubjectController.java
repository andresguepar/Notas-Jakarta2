package com.example.notasjakarta.controllers;

import com.example.notasjakarta.repository.impl.SubjectRepositoryImpl;
import com.example.notasjakarta.services.SubjectService;
import com.example.notasjakarta.services.impl.SubjectServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "subjectController", value = "/subject-form")
public class SubjectController extends HttpServlet {

    private SubjectRepositoryImpl subjectRepository;
    private SubjectService service;

    public SubjectController() {
        /*subjectRepository = new SubjectRepositoryLogicImpl();
        service = new SubjectServiceImpl((Connection) subjectRepository);*/
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        Connection conn = (Connection) request.getAttribute("conn");
        SubjectService service = new SubjectServiceImpl(conn);

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Subjects</h1>");
        out.println(service.list());
        out.println("</body></html>");
    }
}
