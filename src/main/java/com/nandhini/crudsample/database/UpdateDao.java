package com.nandhini.crudsample.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nandhini.crudsample.bean.UpdateBean;

public class UpdateDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/crud?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Samplelogin";
    
    public UpdateBean getEmployeeDetails(int empId) {
        UpdateBean updateBean = new UpdateBean();
        String query = "SELECT * FROM employee WHERE empId = ?";
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        	preparedStatement.setInt(1, empId);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
            	updateBean.setEmpId(resultSet.getInt("empId"));
                updateBean.setEmpName(resultSet.getString("empname"));
                updateBean.setAge(resultSet.getInt("age"));
                updateBean.setDob(resultSet.getDate("dob"));
                updateBean.setJoindate(resultSet.getDate("joindate"));
                updateBean.setPhone(resultSet.getString("phone"));
                updateBean.setEmail(resultSet.getString("email"));
                updateBean.setDesignation(resultSet.getString("designation"));
                updateBean.setDepartmentid(resultSet.getInt("departmentid"));
                updateBean.setSalary(resultSet.getBigDecimal("salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updateBean;
    }

    public boolean updateEmployeeDetails(UpdateBean updateBean) {
    	String sql = "UPDATE employee SET phone = ?, email = ?, designation = ?, departmentId = ?, salary = ? WHERE empId = ?";
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        	
        	preparedStatement.setString(1, updateBean.getPhone());
        	preparedStatement.setString(2, updateBean.getEmail());
        	preparedStatement.setString(3, updateBean.getDesignation());
        	preparedStatement.setInt(4, updateBean.getDepartmentid());
        	preparedStatement.setBigDecimal(5, updateBean.getSalary());
        	preparedStatement.setInt(6, updateBean.getEmpId());
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
