package com.example.spe_majorproject.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Faculty {
    @Id
    private String email;
    private String name;
    private String phone;
    private String office;
    private String designation;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", office='" + office + '\'' +
                ", designation='" + designation + '\'' +
                '}';
    }
}
