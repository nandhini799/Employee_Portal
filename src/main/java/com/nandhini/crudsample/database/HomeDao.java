package com.nandhini.crudsample.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nandhini.crudsample.bean.HomeBean;

public class HomeDao {

    private String jdbcURL = "jdbc:mysql://localhost:3306/crud?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Samplelogin";

    private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM EMPLOYEE";

    public HomeDao() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<HomeBean> selectAllEmployees() {
        List<HomeBean> employees = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                HomeBean employee = new HomeBean();
                employee.setEmpId(rs.getInt("empid"));
                employee.setEmpName(rs.getString("empname"));
                employee.setAge(rs.getInt("age"));
                employee.setDob(rs.getString("dob"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setDepartmentId(rs.getInt("departmentid"));
                employee.setPhone(rs.getString("phone"));
                employee.setEmail(rs.getString("email"));
                employee.setJoinDate(rs.getString("joindate"));
                employee.setDesignation(rs.getString("designation"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return employees;
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
