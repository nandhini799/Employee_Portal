package com.nandhini.crudsample.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import com.nandhini.crudsample.bean.AddBean;
import com.nandhini.crudsample.database.AddDao;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AddDao addDao;

    public void init() {
        addDao = new AddDao();
        System.out.println("AddServlet initialized with AddDao.");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empname = request.getParameter("empname");
        int age = Integer.parseInt(request.getParameter("age"));
        Date dob = Date.valueOf(request.getParameter("dob"));
        BigDecimal salary = new BigDecimal(request.getParameter("salary"));
        int departmentid = Integer.parseInt(request.getParameter("departmentid"));
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        Date joindate = Date.valueOf(request.getParameter("joindate"));
        String designation = request.getParameter("designation");

        AddBean employee = new AddBean(empname, age, dob, salary, departmentid, phone, email, joindate, designation);

        RequestDispatcher dispatcher = request.getRequestDispatcher("add.jsp");
        try {
            boolean result = addDao.AddEmployee(employee);
            if (result) {
                request.setAttribute("status", "success");
            } else {
                request.setAttribute("status", "failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("status", "error");
        }
        dispatcher.forward(request, response);
    }
}
