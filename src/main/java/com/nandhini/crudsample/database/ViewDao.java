package com.nandhini.crudsample.database;

import com.nandhini.crudsample.bean.ViewBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewDao {

    private String jdbcURL = "jdbc:mysql://localhost:3306/crud?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Samplelogin";

    public List<ViewBean> listAllEmployees(String sortField) throws SQLException {
        List<ViewBean> employees = new ArrayList<>();

        // Validate sortField to prevent SQL injection
        if (!isValidSortField(sortField)) {
            sortField = "empname"; // default sort field
        }

        String query = "SELECT * FROM employee ORDER BY " + sortField + " ASC";
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ViewBean employee = new ViewBean();
                employee.setEmpName(resultSet.getString("empName"));
                employee.setAge(resultSet.getInt("age"));
                employee.setDob(resultSet.getDate("dob"));
                employee.setSalary(resultSet.getBigDecimal("salary"));
                employee.setDepartmentid(resultSet.getInt("departmentid"));
                employee.setPhone(resultSet.getString("phone"));
                employee.setEmail(resultSet.getString("email"));
                employee.setJoindate(resultSet.getDate("joindate"));
                employee.setDesignation(resultSet.getString("designation"));
                employee.setEmpId(resultSet.getInt("empId"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return employees;
    }
    public ViewBean getEmployeeById(int empid) throws SQLException {
        ViewBean employee = null;
        String query = "SELECT * FROM employee WHERE empid = ?";
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, empid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employee = new ViewBean();
                employee.setEmpName(resultSet.getString("empName"));
                employee.setAge(resultSet.getInt("age"));
                employee.setDob(resultSet.getDate("dob"));
                employee.setSalary(resultSet.getBigDecimal("salary"));
                employee.setDepartmentid(resultSet.getInt("departmentid"));
                employee.setPhone(resultSet.getString("phone"));
                employee.setEmail(resultSet.getString("email"));
                employee.setJoindate(resultSet.getDate("joindate"));
                employee.setDesignation(resultSet.getString("designation"));
                employee.setEmpId(resultSet.getInt("empId"));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return employee;
    }

    public void deleteEmployee(int empid) throws SQLException {
        String query = "DELETE FROM employee WHERE empid = ?";
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, empid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private boolean isValidSortField(String sortField) {
        return sortField.equals("empname") || sortField.equals("age") || sortField.equals("dob") || 
               sortField.equals("salary") || sortField.equals("departmentid") || sortField.equals("joindate") || 
               sortField.equals("designation") || sortField.equals("empid");
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
