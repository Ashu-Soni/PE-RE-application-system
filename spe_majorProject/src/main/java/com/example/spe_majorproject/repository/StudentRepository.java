package com.example.spe_majorproject.repository;

import com.example.spe_majorproject.bean.Student;
import org.apache.catalina.valves.StuckThreadDetectionValve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,String> {
}
