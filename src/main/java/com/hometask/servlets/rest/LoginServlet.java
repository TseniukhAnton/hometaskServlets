package com.hometask.servlets.rest;

import com.hometask.servlets.repository.hibernate.HiberUserRepoImpl;
import com.hometask.servlets.service.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AuthenticationService authenticationService = new AuthenticationService(new HiberUserRepoImpl());
        if (authenticationService.authenticate(username, password)) {
            HttpSession userSession = request.getSession();
            userSession.setAttribute("username", username);
            userSession.setAttribute("password", password);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
