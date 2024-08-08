<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.nandhini.crudsample.bean.ViewBean" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <meta http-equiv="X-UA-Compatible" content="ie=edge">
   <title>Employee Details</title>
   <style>
       body {
            display: flex;
            flex-direction: column;
		    justify-content: center;
		    align-items: center;
		    height: 100vh;
		    margin: 0;
		    background-color: #D3D3D3;
        }
        .header {
            position: absolute;
            top: 10px;
            left: 20px;
            font-size: 18px;
            color: #333;
        }
        .logout-link {
            position: absolute;
            top: 20px;
            right: 20px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
            background-color: #FF0000;
            color: white;
            text-decoration: none;
            transition: background-color 0.3s;
        }
        .logout-link:hover {
            background-color: #3700b3;
        }
        .top-right-container {
            position: absolute;
            top: 30px;
            right: 130px;
            display: flex;
            align-items: center;
        }
        .top-left-container {
		    position: absolute;
		    top: 20px;
		    left: 20px;
		    font-size: 18px;
		}
		
		.table-container {
		    display: flex;
		    flex-direction: column;
		    align-items: center;
		    width: 80%; 
		}
       table {
           width: 100%; 
           border-collapse: collapse;
       }
       table, th, td {
           border: 1px solid black;
       }
       th, td {
           padding: 15px;
           text-align: left;
       }
       th {
           background-color: #f2f2f2;
       }
       .form-title {
            text-align: center;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
            display: flex;
            align-items: center;
        }
        .form-group label {
            width: 150px; /* Adjust label width */
            margin-right: 15px; /* Space between label and input */
        }
        .form-group input {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 100%;
            box-sizing: border-box;
        }
        .btn {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            background-color: #007BFF;
            color: white;
            cursor: pointer;
            margin-right: 10px;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        .btn:last-child {
            margin-right: 0;
        }
   </style>
</head>
<body>
<%
        String username = (String) session.getAttribute("username");
        //String empType = (String) session.getAttribute("empType");
        if (username == null) {
            response.sendRedirect("login.jsp");
        }
    %>
    <div class="top-right-container">
       Logged In User: <%= username %>!
    </div>
    
    <div class="header">
       <h3>View Employee Details</h3>
    </div>
    <a href="login.jsp" class="logout-link">Logout</a>
    
    
    <div class="main">
        <section class="view">
            <div class="container">
                <div class="view-content">
                    <h2 class="form-title">Employee Details</h2>
                    <table>
                        <thead>
                            <tr>
                                <th><a href="view?sortField=empName">Employee Name</a></th>
                                <th><a href="view?sortField=age">Age</a></th>
                                <th><a href="view?sortField=dob">Date of Birth</a></th>
                                <th><a href="view?sortField=salary">Salary</a></th>
                                <th><a href="view?sortField=departmentid">Department ID</a></th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th><a href="view?sortField=joindate">Join Date</a></th>
                                <th><a href="view?sortField=designation">Designation</a></th>
                                <th><a href="view?sortField=empId">Employee ID</a></th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                @SuppressWarnings("unchecked")
                                List<ViewBean> employees = (List<ViewBean>) request.getAttribute("employees");
                                String empType = (String) session.getAttribute("empType"); // Use implicit session object
                                Integer empId = (Integer) session.getAttribute("empId");// Use implicit session object
                                
                                if (employees != null) {
                                    for (ViewBean employee : employees) {
                                    
                            %>
                                <tr>
                                    <td>
                                        <% if ("admin".equals(empType)) { %>
                                            <a href="update?empid=<%= employee.getEmpId() %>"><%= employee.getEmpName() %></a>
                                        <% } else { %>
                                            <%= employee.getEmpName() %>
                                        <% } %>
                                    </td>
                                    <td><%= employee.getAge() %></td>
                                    <td><%= employee.getDob() %></td>
                                    <td><%= employee.getSalary() %></td>
                                    <td><%= employee.getDepartmentid() %></td>
                                    <td><%= employee.getPhone() %></td>
                                    <td><%= employee.getEmail() %></td>
                                    <td><%= employee.getJoindate() %></td>
                                    <td><%= employee.getDesignation() %></td>
                                    <td><%= employee.getEmpId() %></td>
                                    <td>
                                        <% 
                                            // Show delete link based on employee type
                                            if ("admin".equals(empType) || ("employee".equals(empType) && employee.getEmpId().equals(empId))) { 
                                        %>
                                            <a href="delete?empId=<%= employee.getEmpId() %>" onclick="return confirm('Are you sure you want to delete this employee?')">Delete</a>
                                        <% } %>
                                    </td>
                                </tr>
                            <%
                                    }
                                } else {
                            %>
                                <tr>
                                    <td colspan="11">No employee data available.</td>
                                </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                    <div class="form-group">
                        <button type="button" class="btn" onclick="goBack()">Back</button>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <script>
        function goBack() {
            window.location.href = 'home.jsp';
        }
    </script>
</body>
</html>
