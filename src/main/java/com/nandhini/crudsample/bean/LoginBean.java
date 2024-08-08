package com.nandhini.crudsample.bean;

import java.io.Serializable;

public class LoginBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String emptype;
    private Integer empid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getEmptype() {
		return emptype;
	}

	public void setEmptype(String emptype) {
		this.emptype = emptype;
	}

	public Integer getEmpid() {
		return empid;
	}

	public void setEmpid(Integer empid) {
		this.empid = empid;
	}
}