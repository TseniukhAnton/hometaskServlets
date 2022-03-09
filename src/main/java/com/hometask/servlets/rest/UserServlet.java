package com.hometask.servlets.rest;

import com.hometask.servlets.model.User;
import com.hometask.servlets.repository.hibernate.HiberUserRepoImpl;
import com.hometask.servlets.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserServlet", value = "/users")
public class UserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService(new HiberUserRepoImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getByUsername((String) request.getSession().getAttribute("username"));
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(user));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       User user = userService.getByUsername((String) request.getSession().getAttribute("username"));
       String newUsername = request.getParameter("newUsername");
       String newPassword = request.getParameter("newPassword");
       System.out.println(user.getId());
       userService.update(user, newUsername, newPassword);
    }
}
