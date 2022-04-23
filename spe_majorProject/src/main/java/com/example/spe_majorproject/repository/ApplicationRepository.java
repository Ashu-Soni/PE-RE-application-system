package com.example.spe_majorproject.repository;

import com.example.spe_majorproject.bean.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application,Integer> {
    public List<Application> findByFacultyemail(String facultyemail);
    public Application findByEmailAndEid(String email,Integer eid);
    public List<Application>findByFacultyemailAndStatus(String facultyemail,String status);
    public List<Application> findByEmail(String email);
}
