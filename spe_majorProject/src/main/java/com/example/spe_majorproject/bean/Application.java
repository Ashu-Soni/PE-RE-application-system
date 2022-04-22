package com.example.spe_majorproject.bean;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
public class Application {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int aid;
    private int eid;
    private String email;
    private String facultyemail;
    private String type;
    private String skills;
    private String experience;
    private String faculty; // change this name to "faculty"
    private String studname;
    private String status;

    public Application() {
        aid=0;
        eid=0;
        email="none";
        facultyemail="none";
        type="none";
        skills="none";
        experience="none";
        faculty="none";
        studname="none";
        status="none";
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStudname() {
        return studname;
    }

    public void setStudname(String studname) {
        this.studname = studname;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacultyemail() {
        return facultyemail;
    }

    public void setFacultyemail(String facultyemail) {
        this.facultyemail = facultyemail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "Application{" +
                "aid=" + aid +
                ", eid=" + eid +
                ", email='" + email + '\'' +
                ", facultyemail='" + facultyemail + '\'' +
                ", type='" + type + '\'' +
                ", skills='" + skills + '\'' +
                ", experience='" + experience + '\'' +
                ", faculty='" + faculty + '\'' +
                ", studname='" + studname + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
