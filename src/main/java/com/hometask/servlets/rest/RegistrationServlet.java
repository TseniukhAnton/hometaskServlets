package com.hometask.servlets.rest;

import com.hometask.servlets.repository.hibernate.HiberUserRepoImpl;
import com.hometask.servlets.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet", value = "/reg")
public class RegistrationServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService(new HiberUserRepoImpl());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (userService.getByUsername(username) != null || username == null || password == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        } else {
            userService.save(username, password);
        }
    }
}
