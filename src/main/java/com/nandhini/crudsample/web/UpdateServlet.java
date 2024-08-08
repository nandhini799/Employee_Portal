package com.nandhini.crudsample.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import com.nandhini.crudsample.bean.UpdateBean;
import com.nandhini.crudsample.bean.ViewBean;
import com.nandhini.crudsample.database.UpdateDao;
import com.nandhini.crudsample.database.ViewDao;



@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private UpdateDao updateDao;
    private ViewDao viewDao;
  
    public void init() {
        System.out.println("Initializing UpdateServlet...");
        try {
        	updateDao = new UpdateDao();
        	viewDao = new ViewDao();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to initialize UpdateDao.");
        }
    }

 

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer empId = (Integer) session.getAttribute("empId");
        String empType = (String) session.getAttribute("empType");
        String empIdParam = request.getParameter("empid");
        
        if (empType != null && empId != null) {
            // If admin and empIdParam is provided, use that; otherwise, use session empId
            int targetEmpId = (empType.equals("admin") && empIdParam != null) ? Integer.parseInt(empIdParam) : empId;
            
            ViewBean employee = null;
			try {
				employee = viewDao.getEmployeeById(targetEmpId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            if (employee != null) {
                request.setAttribute("updateBean", employee);
                request.getRequestDispatcher("update.jsp").forward(request, response);
            } else {
                response.sendRedirect("home.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empIdStr = request.getParameter("empId");
        if (empIdStr == null || empIdStr.isEmpty()) {
            request.setAttribute("errorMessage", "Employee ID is missing.");
            request.getRequestDispatcher("update.jsp").forward(request, response);
            return;
        }

        int empId;
        try {
            empId = Integer.parseInt(empIdStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid Employee ID.");
            request.getRequestDispatcher("update.jsp").forward(request, response);
            return;
        }

        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String designation = request.getParameter("designation");
        String departmentIdStr = request.getParameter("departmentId");
        String salaryStr = request.getParameter("salary");

        if (departmentIdStr == null || departmentIdStr.isEmpty() || salaryStr == null || salaryStr.isEmpty()) {
            request.setAttribute("errorMessage", "Department ID and Salary are required.");
            request.getRequestDispatcher("update.jsp").forward(request, response);
            return;
        }

        int departmentId;
        BigDecimal salary;
        try {
            departmentId = Integer.parseInt(departmentIdStr);
            salary = new BigDecimal(salaryStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid Department ID or Salary.");
            request.getRequestDispatcher("update.jsp").forward(request, response);
            return;
        }

        UpdateBean updateBean = new UpdateBean();
        updateBean.setEmpId(empId);
        updateBean.setPhone(phone);
        updateBean.setEmail(email);
        updateBean.setDesignation(designation);
        updateBean.setDepartmentid(departmentId);
        updateBean.setSalary(salary);

        boolean isUpdated = updateDao.updateEmployeeDetails(updateBean);
        if (isUpdated) {
            response.sendRedirect("home.jsp");
        } else {
            request.setAttribute("errorMessage", "Update failed. Please try again.");
            request.getRequestDispatcher("update.jsp").forward(request, response);
        }
    }

    
    
    
}