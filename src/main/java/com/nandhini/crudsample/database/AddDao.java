package com.nandhini.crudsample.database;

import com.nandhini.crudsample.bean.AddBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddDao {

    private String jdbcURL = "jdbc:mysql://localhost:3306/crud?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Samplelogin";
    
    private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employee (empname, age, dob, salary, departmentid, phone, email, joindate, designation, empid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_LATEST_EMP_ID_SQL = "SELECT empid FROM employee ORDER BY empid DESC LIMIT 1";

    public boolean AddEmployee(AddBean employee) throws SQLException {
        boolean rowInserted = false;
        int newEmpID = generateNewEmpID();
        
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL)) {
             
            preparedStatement.setString(1, employee.getEmpname());
            preparedStatement.setInt(2, employee.getAge());
            preparedStatement.setDate(3, employee.getDob());
            preparedStatement.setBigDecimal(4, employee.getSalary());
            preparedStatement.setInt(5, employee.getDepartmentid());
            preparedStatement.setString(6, employee.getPhone());
            preparedStatement.setString(7, employee.getEmail());
            preparedStatement.setDate(8, employee.getJoindate());
            preparedStatement.setString(9, employee.getDesignation());
            preparedStatement.setInt(10, newEmpID);

            rowInserted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rowInserted;
    }

    private int generateNewEmpID() {
        int latestEmpID = 0;
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LATEST_EMP_ID_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
             
            if (resultSet.next()) {
                latestEmpID = resultSet.getInt("empid");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return (latestEmpID > 0) ? (latestEmpID + 1) : 2000;
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
