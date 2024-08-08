<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #D3D3D3; 
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
        .button-container {
            text-align: center;
            margin-top: 100px; /* Adjust as needed to position buttons vertically */
        }
        button {
            padding: 10px 20px;
            margin: 10px;
            font-size: 16px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            background-color: #6200ea;
            color: white;
            transition: background-color 0.3s;
        }
        button:hover {
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
       <h3>Home Page</h3>
    </div>
    
    <a href="login.jsp" class="logout-link">Logout</a>
    <div class="button-container">
        <% if ("admin".equals(empType)) { %>
            <button onclick="window.location.href='add.jsp'">Add</button>
        <% } %>
        <button onclick="window.location.href='update'">Update</button>
        <button onclick="window.location.href='view'">View</button>
    </div>
</body>
</html>
