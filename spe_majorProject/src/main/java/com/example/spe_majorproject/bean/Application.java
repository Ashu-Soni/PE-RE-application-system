package com.example.spe_majorproject.bean;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @GenericGenerator(name = "seq", strategy="increment")
    private int aid;
    private int eid;
    private String studemail;
    private String facultyemail;
    private String type;
    private String skills;
    private String experience;
    private String facultyname;
    private String studname;
    private String status;

    public Application() {
        aid=0;
        eid=0;
        studemail="none";
        facultyemail="none";
        type="none";
        skills="none";
        experience="none";
        facultyname="none";
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

    public String getStudemail() {
        return studemail;
    }

    public void setStudemail(String studemail) {
        this.studemail = studemail;
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

    public String getFacultyname() {
        return facultyname;
    }

    public void setFacultyname(String facultyname) {
        this.facultyname = facultyname;
    }

    @Override
    public String toString() {
        return "Application{" +
                "aid=" + aid +
                ", eid=" + eid +
                ", studemail='" + studemail + '\'' +
                ", facultyemail='" + facultyemail + '\'' +
                ", type='" + type + '\'' +
                ", skills='" + skills + '\'' +
                ", experience='" + experience + '\'' +
                ", facultyname='" + facultyname + '\'' +
                ", studname='" + studname + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
