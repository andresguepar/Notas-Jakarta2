package com.example.notasjakarta.controllers.others;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(value="/test")
public class Test extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        // Configuración de la respuesta HTTP como tipo de contenido HTML
        resp.setContentType("text/html");

        // Obtención del método HTTP utilizado en la solicitud
        String metodoHttp = req.getMethod();

        // Obtención de la URI de la solicitud
        String requestUri = req.getRequestURI();

        // Obtención de la URL completa de la solicitud
        String requestUrl = req.getRequestURL().toString();

        // Obtención del contexto de la aplicación web
        String contexPath = req.getContextPath();

        // Obtención de la ruta del servlet en la solicitud
        String servletPath = req.getServletPath();

        // Obtención de la dirección IP del cliente que realizó la solicitud
        String ipCliente = req.getRemoteAddr();

        // Obtención de la dirección IP local del servidor
        String ip = req.getLocalAddr();

        // Obtención del puerto local del servidor
        int port = req.getLocalPort();

        // Obtención del esquema de la solicitud (HTTP o HTTPS)
        String scheme = req.getScheme();

        // Obtención del encabezado 'host' de la solicitud
        String host = req.getHeader("host");

        // Construcción de la URL completa a partir de los componentes anteriores
        String url = scheme + "://" + host + contexPath + servletPath;

        // Construcción de otra URL completa que incluye la dirección IP y el puerto local
        String url2 = scheme + "://" + ip + ":" + port + contexPath + servletPath;
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println(" <head>");
            out.println(" <meta charset=\"UTF-8\">");
            out.println(" <title>Cabeceras HTTP Request</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println(" <h1>Cabeceras HTTP Request!</h1>");
            out.println("<ul>");
            out.println("<li>metodo http: " + metodoHttp + "</li>");
            out.println("<li>request uri: " + requestUri + "</li>");
            out.println("<li>request url: " + requestUrl + "</li>");
            out.println("<li>context path: " + contexPath + "</li>");
            out.println("<li>servlet path: " + servletPath + "</li>");
            out.println("<li>ip local: " + ip + "</li>");
            out.println("<li>ip cliente: " + ipCliente + "</li>");
            out.println("<li>puerto local: " + port + "</li>");
            out.println("<li>scheme: " + scheme + "</li>");
            out.println("<li>host: " + host + "</li>");
            out.println("<li>url: " + url + "</li>");
            out.println("<li>url2: " + url2 + "</li>");
            Enumeration<String> headerNames = req.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String cabecera = headerNames.nextElement();
                out.println("<li>"+ cabecera + ": " + req.getHeader(cabecera) + "</li>");
            }
            out.println("</ul>");
            out.println(" </body>");
            out.println("</html>");
        }
    }
}
