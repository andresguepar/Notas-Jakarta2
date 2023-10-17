package com.example.notasjakarta.controllers.login;

import com.example.notasjakarta.mapping.dtos.StudentDto;
import com.example.notasjakarta.mapping.dtos.SubjectDto;
import com.example.notasjakarta.mapping.dtos.TeacherDto;
import com.example.notasjakarta.services.LoginService;
import com.example.notasjakarta.services.StudentService;
import com.example.notasjakarta.services.SubjectService;
import com.example.notasjakarta.services.TeacherService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
@RequestScoped
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";
    @Inject
    @Named("login")
    private LoginService auth;
    @Inject
    private TeacherService serviceT;
    @Inject
    private SubjectService serviceS;
    @Inject
    private StudentService serviceSt;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            resp.setContentType("text/html;charset=UTF-8");
            Cookie usernameCookie = new Cookie("username", username);
            resp.addCookie(usernameCookie);

            List<TeacherDto> teacherDtoList = serviceT.list();
            getServletContext().setAttribute("teacherDtoList", teacherDtoList);

            List<SubjectDto> subjectDtoList = serviceS.list();
            getServletContext().setAttribute("subjectDtoList", subjectDtoList);

            List<StudentDto> studentDtoList = serviceSt.list();
            getServletContext().setAttribute("studentDtoList", studentDtoList);

            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Login correcto</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Login correcto!</h1>");
                out.println(" <h3>Hola "+ username + " has iniciado sesión con éxito!</h3>");
                out.println(" </body>");
                out.println("</html>");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado " +
                    "para ingresar a esta página!");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {

        Optional<String> cookieOptional = auth.getUsername(req);
        if (cookieOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Hola " + cookieOptional.get() + "</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Hola " + cookieOptional.get() + " has iniciado sesión con " +
                        "éxito!</h1>");
                out.println("<p><a href='" + req.getContextPath() +
                        "/index.jps'>volver</a></p>");

                out.println("<p><a href='" + req.getContextPath() + "/logout'>cerrar sesión</a></p>");
                out.println(" </body>");
                out.println("</html>");
            }
        }else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }


}

