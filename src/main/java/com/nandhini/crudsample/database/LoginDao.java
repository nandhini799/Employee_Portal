package com.nandhini.crudsample.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nandhini.crudsample.bean.LoginBean;

public class LoginDao {

    public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
        boolean status = false;

        Class.forName("com.mysql.cj.jdbc.Driver");
        
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/crud?useSSL=false", "root", "Samplelogin");

             PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT * FROM login WHERE username = ? AND password = ?")) {
            preparedStatement.setString(1, loginBean.getUsername());
            preparedStatement.setString(2, loginBean.getPassword());

            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
            printSQLException(e);
        }
        return status;
    }

    public String getEmpType(String username) throws ClassNotFoundException {
        String empType = null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/crud?useSSL=false", "root", "Samplelogin");
             PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT emptype FROM login WHERE username = ?")) {
            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                empType = rs.getString("emptype");
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return empType;
    }
    
    public Integer getEmpId(String username) throws ClassNotFoundException {
        Integer empId = null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/crud?useSSL=false", "root", "Samplelogin");
             PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT empid FROM login WHERE username = ?")) {
            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                empId = rs.getInt("empid");
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return empId;
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
