package com.hometask.servlets.rest;

import com.hometask.servlets.model.User;
import com.hometask.servlets.repository.hibernate.HiberEventRepoImpl;
import com.hometask.servlets.repository.hibernate.HiberFileRepoImpl;
import com.hometask.servlets.repository.hibernate.HiberUserRepoImpl;
import com.hometask.servlets.service.EventService;
import com.hometask.servlets.service.FileService;
import com.hometask.servlets.service.UserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet("/files")
public class FileServlet extends HttpServlet {
    private FileService fileService;
    private UserService userService;
    private EventService eventService;

    @Override
    public void init() throws ServletException {
        fileService = new FileService(new HiberFileRepoImpl());
        userService = new UserService(new HiberUserRepoImpl());
        eventService = new EventService(new HiberEventRepoImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getByUsername((String) request.getSession().getAttribute("username"));
        FileInputStream in = null;
        OutputStream out = null;
        String filename = request.getParameter("filename");

        try {
            File file = new File("user_files/" + user.getUsername() + "/" + filename);
            in = new FileInputStream(file);
            response.setContentType("applicaion/octet-stream");
            response.setContentLength((int) file.length());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=\"" + filename + "\"";
            response.setHeader(headerKey, headerValue);

            out = response.getOutputStream();
            int length;

            byte[] buffer = new byte[4096];
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            eventService.save("download", fileService.getFileByName(filename, user), user);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_CONFLICT);
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = userService.getByUsername((String) request.getSession().getAttribute("username"));

            ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> multifiles = servletFileUpload.parseRequest(request);
            for (FileItem item : multifiles) {
                item.write(new File(new File("user_files/") + user.getUsername() + "/" + item.getName()));
                fileService.save(item.getName(), user);
                eventService.save("Save", fileService.getFileByName(item.getName(), user), user);
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userService.getByUsername((String) request.getSession().getAttribute("username"));
        String fileName = request.getParameter("filename");
        try {
            File file = new File("user_files/" + user.getUsername() + "/" + fileName);
            file.delete();
            fileService.deleteById(fileService.getFileByName(fileName, user).getId());
            eventService.save("Delete", fileService.getFileByName(fileName, user), user);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }


}
