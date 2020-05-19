package org.example.servlet;

import org.example.libs.UserNotFoundException;
import org.example.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WelcomeServlet extends HttpServlet {
    private final AuthService authService;

    public WelcomeServlet(AuthService authService) {
        this.authService = authService;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("%LOGGED%")){
                String[] split = cookie.getValue().split("-");
                try {
                    if(authService.loginPlain(split[0],split[1])) {
                        Path path = Paths.get("./source/welcome.html");
                        ServletOutputStream outputStream = resp.getOutputStream();
                        Files.copy(path, outputStream);
                    }
                    else
                        resp.sendRedirect("/");
                } catch (UserNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
