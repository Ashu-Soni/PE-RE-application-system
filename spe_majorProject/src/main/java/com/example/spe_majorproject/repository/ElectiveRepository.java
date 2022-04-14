package com.example.spe_majorproject.repository;

import com.example.spe_majorproject.bean.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ElectiveRepository extends JpaRepository<Elective,Integer> {
    List<Elective> findByType(String Type);
}
