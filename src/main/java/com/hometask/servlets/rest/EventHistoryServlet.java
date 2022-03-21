package com.hometask.servlets.rest;

import com.hometask.servlets.model.User;
import com.hometask.servlets.repository.hibernate.HiberEventRepoImpl;
import com.hometask.servlets.repository.hibernate.HiberUserRepoImpl;
import com.hometask.servlets.service.EventService;
import com.hometask.servlets.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EventHistoryServlet", value = "/events")
public class EventHistoryServlet extends HttpServlet {
    private UserService userService;
    private EventService eventService;

    @Override
    public void init() throws ServletException {
       userService = new UserService(new HiberUserRepoImpl());
       eventService = new EventService(new HiberEventRepoImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.getByUsername((String) req.getSession().getAttribute("username"));
        List events = eventService.getUserEvents(user);
        resp.setContentType("application/json");
        resp.getWriter().write(new ObjectMapper().writeValueAsString(events));
    }
}
