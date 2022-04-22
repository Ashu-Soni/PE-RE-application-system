package com.example.spe_majorproject.repository;

import com.example.spe_majorproject.bean.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application,Integer> {
    public List<Application> findByFacultyemail(String facultyemail);
}
