
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.nandhini.crudsample.bean.UpdateBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Update Employee Details</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #D3D3D3;
        }
        .container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 600px; /* Set width as needed */
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
        .status {
            text-align: center;
            margin-top: 20px;
        }
        .top-right-container {
            position: absolute;
            top: 30px;
            right: 130px;
            display: flex;
            align-items: center;
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
    </style>
 
</head>
<body>
<%
        String username = (String) session.getAttribute("username");
        String empType = (String) session.getAttribute("empType");
        if (username == null) {
            response.sendRedirect("login.jsp");
        }
    %>
    <div class="top-right-container">
       Logged In User: <%= username %>!
    </div>
    
    <div class="header">
       <h3>Update Employee Details</h3>
    </div>
    <a href="login.jsp" class="logout-link">Logout</a>
    
    <div class="main">
        <section class="update">
            <div class="container">
                <div class="update-content">
                    <h2 class="form-title">Update Employee Details</h2>
                    <form action="UpdateServlet" method="post">
                        <input type="hidden" name="empId" value="${updateBean.empId}" />

                        <div class="form-group">
                            <label>Employee Name</label>
                            <input type="text" name="empName" value="${updateBean.empName}" readonly />
                        </div>

                        <div class="form-group">
                            <label>Age</label>
                            <input type="number" name="age" value="${updateBean.age}" readonly />
                        </div>

                        <div class="form-group">
                            <label>Date of Birth</label>
                            <input type="date" name="dob" value="${updateBean.dob}" readonly />
                        </div>

                        <div class="form-group">
                            <label>Join Date</label>
                            <input type="date" name="joindate" value="${updateBean.joindate}" readonly />
                        </div>

                        <div class="form-group">
                            <label>Phone</label>
                            <input type="text" name="phone" value="${updateBean.phone}" />
                        </div>

                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" name="email" value="${updateBean.email}" />
                        </div>

                        <div class="form-group">
                            <label>Designation</label>
                            <input type="text" name="designation" value="${updateBean.designation}" />
                        </div>

                        <div class="form-group">
                            <label>Department ID</label>
                            <input type="number" name="departmentId" value="${updateBean.departmentid}" />
                        </div>

                        <div class="form-group">
                            <label>Salary</label>
                            <input type="text" name="salary" value="${updateBean.salary}" />
                        </div>

                        <div class="form-group">
                            <button type="submit" class="btn">Update</button>
                        </div>
                    </form>
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
