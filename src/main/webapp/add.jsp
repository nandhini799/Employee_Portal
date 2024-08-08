<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Add Employee</title>
    <link rel="stylesheet" href="https://unpkg.com/sweetalert/dist/sweetalert.css">
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
       <h3>Add Employee Details</h3>
    </div>
    <a href="login.jsp" class="logout-link">Logout</a>
    
    <input type="hidden" id="status" value="<%= request.getAttribute("status") %>">

    <div class="container">
        <h2 class="form-title">Add Employee</h2>
        <form method="post" action="add" onsubmit="return validateForm()">
            <div class="form-group">
                <label for="empname">Name:</label>
                <input type="text" name="empname" id="empname" required pattern="[A-Za-z\s]+" oninput="validateName()"/>
                <span id="nameError" style="color:red;display:none;">Provide your name! No numbers or special characters are allowed.</span>
            </div>
            <div class="form-group">
                <label for="dob">DOB:</label>
                <input type="date" name="dob" id="dob" required onchange="validateDOB()"/>
                <span id="dobError" style="color:red;display:none;">Invalid date of birth. Employee must  be at least 18 years old .</span>
            </div>
            <div class="form-group">
                <label for="age">Age:</label>
                <input type="text" name="age" id="age" readonly/>
            </div>
            <div class="form-group">
                <label for="salary">Salary:</label>
                <input type="text" name="salary" id="salary" required pattern="\d+(\.\d{1,2})?"/>
            </div>
            <div class="form-group">
                <label for="departmentid">Department:</label>
                <input type="text" name="departmentid" id="departmentid" required/>
            </div>
            <div class="form-group">
                <label for="phone">Phone:</label>
                <input type="text" name="phone" id="phone" required oninput="validatePhone()"/>
                <span id="phoneError" style="color:red;display:none;">Please enter a valid 10-digit phone number.</span>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" name="email" id="email" required onchange="validateEmail()"/>
                <span id="emailError" style="color:red;display:none;">Please enter a valid email ID.</span>
            </div>
            <div class="form-group">
                <label for="joindate">Join Date:</label>
                <input type="date" name="joindate" id="joindate" required/>
            </div>
            <div class="form-group">
                <label for="designation">Designation:</label>
                <input type="text" name="designation" id="designation" required/>
            </div>
            <div class="form-group">
                <button type="submit" class="btn" onclick="return confirm('Hey Admin! Are you sure you want to add a new employee to the Employee Table?')">Save</button>
                <button type="button" class="btn" onclick="resetForm()">Reset</button>
                <button type="button" class="btn" onclick="goBack()">Back</button>
            </div>
        </form>
        <div class="status">
            <% 
            String status = (String) request.getAttribute("status");
            if ("success".equals(status)) { %>
                <script>
                    swal("Congrats", "Account Created Successfully", "success");
                </script>
            <% } else if ("failed".equals(status)) { %>
                <script>
                    swal("Oops", "Account Creation Failed", "error");
                </script>
            <% } %>
        </div>
    </div>

    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script>
        function goBack() {
            window.location.href = 'home.jsp';
        }

        function validateName() {
            const empname = document.getElementById("empname");
            const nameError = document.getElementById("nameError");

            const pattern = /^[A-Za-z\s]+$/;
            if (!pattern.test(empname.value)) {
                nameError.style.display = "block";
            } else {
                nameError.style.display = "none";
            }
        }

        function calculateAge() {
            var dob = document.getElementById("dob").value;
            var ageField = document.getElementById("age");
            var dobError = document.getElementById("dobError");
            var currentDate = new Date();
            var selectedDate = new Date(dob);

            if (dob) {
                var ageDiff = currentDate.getFullYear() - selectedDate.getFullYear();
                var monthDiff = currentDate.getMonth() - selectedDate.getMonth();
                var dayDiff = currentDate.getDate() - selectedDate.getDate();
                
                if (monthDiff < 0 || (monthDiff === 0 && dayDiff < 0)) {
                    ageDiff--;
                }

                ageField.value = ageDiff;

                if (ageDiff < 18 || selectedDate > currentDate) {
                    dobError.style.display = "inline";
                } else {
                    dobError.style.display = "none";
                }
            } else {
                ageField.value = "";
            }
        }

        function validateDOB() {
            var dob = document.getElementById("dob").value;
            var dobError = document.getElementById("dobError");
            var currentDate = new Date();
            var selectedDate = new Date(dob);

            if (dob) {
                var ageDiff = currentDate.getFullYear() - selectedDate.getFullYear();
                var monthDiff = currentDate.getMonth() - selectedDate.getMonth();
                var dayDiff = currentDate.getDate() - selectedDate.getDate();

                if (ageDiff < 18 || (ageDiff === 18 && (monthDiff < 0 || (monthDiff === 0 && dayDiff < 0))) || selectedDate > currentDate) {
                    dobError.style.display = "inline";
                } else {
                    dobError.style.display = "none";
                }
            } else {
                dobError.style.display = "none";
            }

            calculateAge();
        }

        function validateEmail() {
            const email = document.getElementById("email");
            const emailError = document.getElementById("emailError");

            const pattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
            if (!pattern.test(email.value)) {
                emailError.style.display = "block";
            } else {
                emailError.style.display = "none";
            }
            calculateAge();
        }

        function validatePhone() {
            var phone = document.getElementById("phone").value;
            var phoneError = document.getElementById("phoneError");
            var phonePattern = /^\d{10}$/;
            if (!phonePattern.test(phone)) {
                phoneError.style.display = "inline";
            } else {
                phoneError.style.display = "none";
            }
        }

        function validateForm() {
            validateName();
            validateDOB();
            validatePhone();
            validateEmail();

            var nameError = document.getElementById("nameError").style.display;
            var dobError = document.getElementById("dobError").style.display;
            var phoneError = document.getElementById("phoneError").style.display;
            var emailError = document.getElementById("emailError").style.display;

            if (nameError === "none" && dobError === "none" && phoneError === "none" && emailError === "none") {
                return true;
            } else {
                return false;
            }
        }

        function resetForm() {
            document.getElementById("empname").value = "";
            document.getElementById("age").value = "";
            document.getElementById("dob").value = "";
            document.getElementById("salary").value = "";
            document.getElementById("departmentid").value = "";
            document.getElementById("phone").value = "";
            document.getElementById("email").value = "";
            document.getElementById("joindate").value = "";
            document.getElementById("designation").value = "";

            document.getElementById("nameError").style.display = "none";
            document.getElementById("dobError").style.display = "none";
            document.getElementById("phoneError").style.display = "none";
            document.getElementById("emailError").style.display = "none";
        }
    </script>
</body>
</html>
