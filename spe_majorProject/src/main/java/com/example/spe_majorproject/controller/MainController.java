package com.example.spe_majorproject.controller;

import com.example.spe_majorproject.repository.UserCredentialsRepository;
//import com.example.spe_majorproject.services.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.spe_majorproject.bean.*;

import java.util.List;


@RestController
@RequestMapping("/home")
public class MainController {

//	private LoginService login=new LoginService();

	@Autowired
	private UserCredentialsRepository usercredrepo;

	@PostMapping("/login")
	public Boolean validateUser(@RequestBody UserCredentials cred)
	{
//		System.out.println(cred);
//		return login.validateUser(cred);

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

//	@GetMapping("/getAllCred")
//	public List<UserCredentials> getAllCredentials()
//	{
//		return login.getAllUserCredentials();
//	}

}
