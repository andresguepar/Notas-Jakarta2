package com.example.notasjakarta.controllers.login;


import com.example.notasjakarta.services.LoginService;
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
import java.util.Optional;
@RequestScoped
@WebServlet("/logout")
public class Logout extends HttpServlet {
    @Inject
    @Named("login")
    private LoginService auth;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
                IOException {

            Optional<String> username = auth.getUsername(req);
            if (username.isPresent()) {
                Cookie usenameCookie = new Cookie("username","");
                usenameCookie.setMaxAge(0);
                resp.addCookie(usenameCookie);
            }

            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }

}

