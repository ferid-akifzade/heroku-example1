package org.example.servlet;

import lombok.extern.slf4j.Slf4j;
import org.example.libs.MutableString;
import org.example.libs.User;
import org.example.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Slf4j
public class HelloWorld extends HttpServlet {
    private final AuthService authService;

    public HelloWorld(AuthService authService) {
        this.authService = authService;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path path = Paths.get("./source/index.html");
        ServletOutputStream outputStream = resp.getOutputStream();
        Files.copy(path, outputStream);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String pass = req.getParameter("pass");
        MutableString redAddress = new MutableString("/");
        Optional<User> user = authService.login(new User(name, pass));
        user.ifPresent(u -> {
            Cookie cookie = new Cookie("%LOGGED%", String.join("-", u.getUsername(), u.getPassword()));
            resp.addCookie(cookie);
            redAddress.update("/welcome");
        });
        resp.sendRedirect(redAddress.getValue());
    }
}

