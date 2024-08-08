package com.nandhini.crudsample.bean;

import java.math.BigDecimal;
import java.sql.Date;

public class AddBean {
    private String empname;
    private int age;
    private Date dob;
    private BigDecimal salary;
    private int departmentid;
    private String phone;
    private String email;
    private Date joindate;
    private String designation;

    public AddBean(String empname, int age, Date dob, BigDecimal salary, int departmentid, String phone, String email, Date joindate, String designation) {
        this.empname = empname;
        this.age = age;
        this.dob = dob;
        this.salary = salary;
        this.departmentid = departmentid;
        this.phone = phone;
        this.email = email;
        this.joindate = joindate;
        this.designation = designation;
    }

    // Getters and Setters
    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getJoindate() {
        return joindate;
    }

    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
