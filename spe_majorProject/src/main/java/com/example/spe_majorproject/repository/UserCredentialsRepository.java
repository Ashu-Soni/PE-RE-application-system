package com.example.spe_majorproject.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.spe_majorproject.bean.*;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials,String> {

}
