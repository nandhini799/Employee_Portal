<h1> Project Overview: </h1>

 The application was developed using Java Servlets, JSP, and a backend database to manage employee records.
 The main functionalities include creating, reading, updating, and deleting (CRUD) operations for employee details, along with a robust login system.

<h3> 1. Database Design: </h3>
The database consists of three tables:

EMPLOYEE: Stores employee details including empid, empname, age, dob, salary, departmentid, phone, email, joindate, and designation.
DEPARTMENT: Stores department information with fields departmentid and deptname.
LOGIN: Manages user credentials with fields username, password, and emptype (to distinguish between admin and non-admin users).

<h3>2. JSP Pages Developed:</h3>

Login.jsp:

Validates the username and password fields.
On clicking the Login button, the credentials are validated against the Login table.
A Reset button clears the username and password fields.
Displays error messages for invalid users and redirects valid users to the Home Page.

<img width="1458" alt="Screenshot 2024-08-09 at 4 22 57 PM" src="https://github.com/user-attachments/assets/bcb63fc6-4aae-406f-828e-bcdc30787358">


Home.jsp:

Contains buttons for Add, Update, and View operations.
Includes a Logout hyperlink that redirects to the Login page.
Provides differentiated access based on the user type (Admin or Non-Admin).

<img width="1464" alt="Screenshot 2024-08-09 at 4 23 23 PM" src="https://github.com/user-attachments/assets/5a8d366b-63b7-4866-bba4-02cf66f7317a">


Add.jsp:

Allows Admin users to add new employee details.
Department is a dropdown menu displaying department names but saving the departmentid in the database.
Includes comprehensive validation for all fields.
The Employee ID is auto-generated based on the latest entry in the database.

<img width="1466" alt="Screenshot 2024-08-09 at 4 24 34 PM" src="https://github.com/user-attachments/assets/dcf8546f-8b2a-44f2-ade7-8a4549994587">


Update.jsp:

Displays the current logged-in employee's details for editing.
Non-admin users can only update their own details, whereas Admin users can update any employee's details.

<img width="1458" alt="Screenshot 2024-08-09 at 4 26 05 PM" src="https://github.com/user-attachments/assets/e8cc8f81-236c-4087-9680-846aa7f1a84c">


View.jsp:

Displays all employee records.
Admin users can edit or delete any record, with hyperlinks enabled for these actions.
Non-admin users can view records and update or delete their own data only.
This Application providing a user-friendly interface for managing employee records efficiently.
The sorting and validation features, along with role-based access control, ensure a secure and organized system.

<img width="1448" alt="Screenshot 2024-08-09 at 4 25 10 PM" src="https://github.com/user-attachments/assets/d886e332-c7a2-4358-87af-3c6d8f05e67c">


<h3>3. Key Functionalities Implemented:</h3>

Sorting:

Users can sort employee records by Name, Age, DOB, Department, etc., by clicking the column headers.
Sorting is not applicable to phone numbers and email addresses.
By default, records are sorted by Name in ascending order.

Deletion:

Admin users can delete any employee record, which also removes the corresponding Login details.
Non-admin users can only delete their own records.

Field Validations:

Name field: No special characters or numbers.
DOB: Minimum age of 18 years and no future dates allowed.
Email: Standard email format (charactersnumber@domain.com).
Phone Number: Must be a 10-digit number.
Age is auto-calculated based on DOB and is a non-editable field.


