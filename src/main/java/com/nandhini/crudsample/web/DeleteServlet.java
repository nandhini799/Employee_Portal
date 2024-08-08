package com.nandhini.crudsample.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import com.nandhini.crudsample.database.ViewDao;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ViewDao viewDao;

    public void init() {
        viewDao = new ViewDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int empId = Integer.parseInt(request.getParameter("empId"));
        try {
            viewDao.deleteEmployee(empId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("view");
    }
}
