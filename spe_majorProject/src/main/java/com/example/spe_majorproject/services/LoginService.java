package com.example.spe_majorproject.services;

import com.example.spe_majorproject.bean.UserCredentials;
import com.example.spe_majorproject.repository.UserCredentialsRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
public class LoginService {

    @Autowired
    private UserCredentialsRepository usercredrepo;

    public Boolean validateUser(@RequestBody @NotNull UserCredentials cred)
    {
        System.out.println("In Service : "+cred);

            if(!cred.getEmail().isEmpty()) {
                System.out.println("In Service : "+cred.getEmail());
                UserCredentials usr = usercredrepo.findById(cred.getEmail()).orElse(new UserCredentials());
                System.out.println("Here:"+usr);
                if(usr.getPassword()!=null && usr.getPassword().equals(cred.getPassword())) {
                    return true;
                }
            }

        return false;
    }
    public List<UserCredentials> getAllUserCredentials()
    {
        return usercredrepo.findAll();
    }
}
