<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Form</title>
<style>
  body {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
    font-family: Arial, sans-serif;
    background-color: #D3D3D3; /* Light grey background */
  }
  .error-message {
    color: red;
    margin-bottom: 10px;
  }
  .login-container {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  .login-container h1 {
    margin-top: -100px; /* Adjust as needed to move the heading closer to the top */
  }
  .login-form {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  .login-form table {
    margin: 20px 0;
  }
  .login-form input[type="submit"],
  .login-form input[type="reset"] {
    margin-top: 1cm; /* Position buttons 2 cm below the password field */
    padding: 10px 20px;
    font-size: 16px;
    cursor: pointer;
    border-radius: 5px;
    border: none;
    background-color: #6200ea;
    color: white;
    transition: background-color 0.3s;
  }
  .login-form input[type="reset"] {
    background-color: #ea6200; /* Different color for reset button */
  }
  .login-form input[type="submit"]:hover {
    background-color: #3700b3;
  }
  .login-form input[type="reset"]:hover {
    background-color: #b33700;
  }
  .button-container {
    display: flex;
    justify-content: center;
    gap: 10px; /* Adjust the gap between buttons as needed */
    margin-top: 1cm; /* Position buttons 2 cm below the password field */
}
</style>
<script>
  // JavaScript function to refresh the page when reset is clicked
  function resetForm() {
    window.location.href = "<%=request.getContextPath()%>/login.jsp"; // Redirect to login.jsp
  }
</script>
</head>
<body>
 <div class="login-container">
  <h1>Employee Login Form</h1>
  <!-- Display error message if present -->
  <%
      String errorMessage = (String) request.getAttribute("errorMessage");
      if (errorMessage != null) {
  %>
      <div class="error-message"><%= errorMessage %></div>
  <%
      }
  %>
  <form action="<%=request.getContextPath()%>/login" method="post" class="login-form">
   <table style="with: 100%">
    <tr>
     <td>UserName</td>
     <td><input type="text" name="username" /></td>
    </tr>
    <tr>
     <td>Password</td>
     <td><input type="password" name="password" /></td>
    </tr>
   </table>
   <div class="button-container">
    <input type="submit" value="Login" />
    <input type="reset" value="Reset" onclick="resetForm()" />
</div>
  </form>
 </div>
</body>
</html>