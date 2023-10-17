package com.example.notasjakarta.controllers.listener;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionListener;

/*
1. Investigacion
“getAttribute()” el cual viene del objeto “HttpRequest” y es válido solo para la solicitud actual y no es
 compartido entre diferentes solicitudes o sesiones.Nos sirve para pasar información específica de una solicitud a
 otra dentro del mismo flujo de solicitud-respuesta.

“getServletContext().getAttribute” el cual viene del objeto “ServletContext” es válido para toda la aplicación y
es compartido entre todas las solicitudes y sesiones. Es útil para almacenar datos globales que deben estar disponibles
 en toda la aplicación.

 2. En un servlet context podria registrar la informacion del usurio que accedio
 y para un request context enviar la infomarcion a un servidor
 */

@WebListener
public class ApplicationListener implements ServletContextListener,
        ServletRequestListener, HttpSessionListener {
    private ServletContext servletContext;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("inicia la aplicación!");
        servletContext = sce.getServletContext();
        servletContext.setAttribute("mensaje", "Hola a todos desde la application!");
        System.out.println("Hola desde context initializer");
    }
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("inicializando el request!");
        sre.getServletRequest().setAttribute("mensaje", "guardando algún valor para el request");
        System.out.println("Hola desde context request ");
    }
}

