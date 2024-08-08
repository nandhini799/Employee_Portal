package com.nandhini.crudsample.web;

//ViewServlet.java
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nandhini.crudsample.bean.ViewBean;
import com.nandhini.crudsample.database.ViewDao;

@WebServlet("/view")
public class ViewServlet extends HttpServlet {
 private static final long serialVersionUID = 1L;
 private ViewDao viewDao;
 
 public void init() {
     System.out.println("Initializing ViewServlet...");
     try {
         viewDao = new ViewDao();
         System.out.println("ViewServlet initialized with ViewDao.");
     } catch (Exception e) {
         e.printStackTrace();
         System.out.println("Failed to initialize ViewDao.");
     }
 }
 
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 System.out.println("Handling GET request in ViewServlet.");
	 
	 String sortField = request.getParameter("sortField");
     if (sortField == null || sortField.isEmpty()) {
         sortField = "empName"; // default sorting by empname
     }
	 
     List<ViewBean> employees = new ArrayList<>();
	try {
		employees = viewDao.listAllEmployees(sortField);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     System.out.println("Number of employees before forwarding: " + employees.size()); // Debug output
     request.setAttribute("employees", employees);
     request.getRequestDispatcher("/view.jsp").forward(request, response);
 }
}


