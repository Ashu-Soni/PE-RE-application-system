package com.example.spe_majorproject.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Elective{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eid;
    private String name;
    private String description;
    private int vacancy;
    private String faculty;
    private String email;
    private String type;

    public Elective() {
        name="none";
        description="none";
        vacancy=0;
        faculty="none";
        email="none";
        type="none";
    }

    public Elective(int eid, String name, String description, int vacancy, String faculty, String email, String type) {
        this.eid = eid;
        this.name = name;
        this.description = description;
        this.vacancy = vacancy;
        this.faculty = faculty;
        this.email = email;
        this.type = type;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVacancy() {
        return vacancy;
    }

    public void setVacancy(int vacancy) {
        this.vacancy = vacancy;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Elective{" +
                "eid=" + eid +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", vacancy='" + vacancy + '\'' +
                ", faculty='" + faculty + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

