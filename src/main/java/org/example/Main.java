package org.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.example.dao.UsersDAO;
import org.example.service.AuthService;
import org.example.servlet.HelloWorld;
import org.example.servlet.WelcomeServlet;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(443);

        ServletContextHandler handler = new ServletContextHandler();
        AuthService service = new AuthService();
        UsersDAO.insert();
        handler.addServlet(new ServletHolder(new WelcomeServlet(service)),"/welcome");
        handler.addServlet(new ServletHolder(new HelloWorld(service)),"/*");
        server.setHandler(handler);
        server.start();
        server.join();
    }
}
