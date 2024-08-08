package com.nandhini.crudsample.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.nandhini.crudsample.bean.LoginBean;
import com.nandhini.crudsample.database.LoginDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDao loginDao;

    public void init() {
        loginDao = new LoginDao();
        System.out.println("LoginServlet initialized with LoginDao.");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("Login attempt - Username: " + username + ", Password: " + (password != null ? "****" : "null")); // Debug: Print username and masked password
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        try {
            if (loginDao.validate(loginBean)) {
                String empType = loginDao.getEmpType(username); // Get the employee type
                Integer empId = loginDao.getEmpId(username);// Get the employee ID
                System.out.println("Employee Type for Username: " + username + " is " + empType); // Debug: Print employee type
                System.out.println("Employee Id for Username: " + username + " is " + empId); // Debug: Print employee id
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("empType", empType);  // Store employee type in session
                session.setAttribute("empId", empId);  // Store employee ID in session
                response.sendRedirect("home.jsp");
                System.out.println("Redirecting to home.jsp for Username: " + username); // Debug: Print redirection
            } else {
                request.setAttribute("errorMessage", "Username or Password is incorrect");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ClassNotFoundException occurred: " + e.getMessage()); // Debug: Print exception message
        }
    }
}