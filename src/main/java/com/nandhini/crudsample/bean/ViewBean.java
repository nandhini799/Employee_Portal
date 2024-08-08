package com.nandhini.crudsample.bean;

import java.math.BigDecimal;
import java.sql.Date;

//ViewBean.java
public class ViewBean {
 private String empName;
 private Integer age;
 private Date dob;
 private BigDecimal salary;
 private Integer departmentid;
 private String phone;
 private String email;
 private Date joindate;
 private String designation;
 private Integer empId;

 // Getters and Setters
 public String getEmpName() { return empName; }
 public void setEmpName(String empName) { this.empName = empName; }

 public Integer getAge() { return age; }
 public void setAge(Integer age) { this.age = age; }

 public Date getDob() { return dob; }
 public void setDob(Date dob) { this.dob = dob; }

 public BigDecimal getSalary() { return salary; }
 public void setSalary(BigDecimal salary) { this.salary = salary; }

 public Integer getDepartmentid() { return departmentid; }
 public void setDepartmentid(Integer departmentid) { this.departmentid = departmentid; }

 public String getPhone() { return phone; }
 public void setPhone(String phone) { this.phone = phone; }

 public String getEmail() { return email; }
 public void setEmail(String email) { this.email = email; }

 public Date getJoindate() { return joindate; }
 public void setJoindate(Date joindate) { this.joindate = joindate; }

 public String getDesignation() { return designation; }
 public void setDesignation(String designation) { this.designation = designation; }

 public Integer getEmpId() { return empId; }
 public void setEmpId(Integer empId) { this.empId = empId; }
}
